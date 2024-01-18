package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.config.test.DbConfig;
import com.cih.shoppingmallcih.dto.test.BoardDTO;
import com.cih.shoppingmallcih.dto.test.BoardListAllDTO;
import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.dto.test.PageResponseDTO;
import com.cih.shoppingmallcih.service.test.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @Value("${org.zerock.upload.path]")
    private String uploadPath;

    @Qualifier("first")
    private final BoardService boardService;

    private final DbConfig dbConfig;

    @GetMapping("/list")
    public String list(@ModelAttribute("dto") PageRequestDTO pageRequestDTO, Model model){
        //@ModelAttribute 를 지정하면. JSP 파일에서 PageRequestDTO 명 말고 ${dto}로 접근 가능
        // 근데 굳이 지정할 필요는 없을듯.

        log.info(pageRequestDTO);

        //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        //PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);
        PageResponseDTO<BoardListAllDTO> responseDTO
                = boardService.listWithAll(pageRequestDTO);

        log.info(responseDTO);

        // DbConfig를 controller 에서 사용한 예
        log.info(dbConfig.toString());  // UserName: sa, password: password!

        model.addAttribute("responseDTO", responseDTO);
        return "/test/board/list";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public String registerGET(){

        log.info("board.....register...get");
        return "/test/board/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info("board POST register......");

        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info("boardDTO: " + boardDTO);

        Long bno = boardService.register(boardDTO);

        // 쿼리 스트링으로 처리 되지 않음, 브라우저 경로에 보이지 않음
        // 일회성으로 데이터를 전송 할때 사용
        redirectAttributes.addFlashAttribute("result", bno);

        // 쿼리 스트링으로 처리 , 브라우저 경로에 보임
        //redirectAttributes.addAttribute("adAttribute-value", "abcdf");

        return "redirect:/board/list";
    }
    @PreAuthorize("isAuthenticated()")      // 로그인한 사용자만 제한
    @GetMapping({"/read", "/modify"})
    public String read(HttpServletRequest request, Long bno, PageRequestDTO pageRequestDTO, Model model){

        String requestURI = request.getRequestURI();

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info("get-read:" + pageRequestDTO);

        model.addAttribute("dto", boardDTO);

        log.info("/test" + requestURI);
        return "/test" + requestURI;
    }
    @PreAuthorize("principal.username == #boardDTO.writer")
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        log.info("board modify post...." + boardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors.....");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?" + link;
        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/board/read";
    }

    @PreAuthorize("principal.username == #boardDTO.writer") // 만일 다른 사용자면 AccessDeniedHandler
        // 가 작성되어 로그인 페이지로 리다이렉트
    @PostMapping("/remove")
    public String remove( BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        log.info("remove......post: " + boardDTO);

        boardService.remove(boardDTO.getBno());

        // 게시물이 db에서 삭제되었다면 첨부파일 삭제
        log.info(boardDTO.getFileNames());

        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/list";
    }

    public void removeFiles(List<String> files){
        for (String fileName : files) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();

            try{
                String contentType = Files.probeContentType(resource.getFile().toPath());

                resource.getFile().delete();

                // 섬네일이 존재한다면
                if(contentType.startsWith("image")){
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);

                    thumbnailFile.delete();
                }
            }catch(Exception e){
                log.error(e.getMessage());
            }
        }
    }


}
