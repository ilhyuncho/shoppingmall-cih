package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.dto.test.upload.UploadFileDTO;
import com.cih.shoppingmallcih.dto.test.upload.UploadResultDTO;
import io.swagger.annotations.ApiOperation;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

                // 겹치지 않는 이름 생성
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

    @ApiOperation(value="view 파일", notes="GET방식으로 첨부파일 조회")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @ApiOperation(value="remove 파일", notes="DELETE 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try{

            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            // 썸네일이 존재한다면
            if(contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);

                thumbnailFile.delete();
            }

        }catch(Exception e){
            log.error(e.getMessage());
        }

        resultMap.put("result", removed);

        return resultMap;
    }



}
