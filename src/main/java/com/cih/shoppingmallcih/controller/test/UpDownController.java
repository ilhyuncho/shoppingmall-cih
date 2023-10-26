package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.dto.test.upload.UploadFileDTO;
import com.cih.shoppingmallcih.dto.test.upload.UploadResultDTO;
import io.swagger.annotations.ApiOperation;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

    @Value("${org.zerock.upload.path}")
    private String uploadPath;
    @ApiOperation(value="Upload post", notes="POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO){

        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null){

            final List<UploadResultDTO> resultDTOList = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile -> {
                String originalName = multipartFile.getOriginalFilename();

                log.info(originalName);

                boolean image = false;

                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid+"_" + originalName);

                try{

                    multipartFile.transferTo(savePath); // 실제 파일 저장

                    // 이미지 파일의 종류라면 (파일의 확장자를 이용하여 마임타입을 판단 )
                    // image/gif, image/png, image/jpeg, image/bmp, image/webp
                    if(Files.probeContentType(savePath).startsWith("image")){

                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);

                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);

                        image = true;
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }

                // return 객체 생성
                UploadResultDTO uploadResultDTO = UploadResultDTO.builder()
                        .fileName(originalName)
                        .uuid(uuid)
                        .img(image).build();

                resultDTOList.add(uploadResultDTO);

            });

            return resultDTOList;
        }

       return null;
    }
}
