package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.config.test.DbConfig;
import com.cih.shoppingmallcih.dto.test.BoardDTO;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.dto.test.PageResponseDTO;
import com.cih.shoppingmallcih.service.test.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @Qualifier("first")
    private final BoardService boardService;

    private final DbConfig dbConfig;

    @GetMapping("/list")
    public String list(@ModelAttribute("dto") PageRequestDTO pageRequestDTO, Model model){
        //@ModelAttribute 를 지정하면. JSP 파일에서 PageRequestDTO 명 말고 ${dto}로 접근 가능
        // 근데 굳이 지정할 필요는 없을듯.

        log.info(pageRequestDTO);

        //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);

        log.info(responseDTO);

        // DbConfig를 controller 에서 사용한 예
        log.info(dbConfig.toString());  // UserName: sa, password: password!

        model.addAttribute("responseDTO", responseDTO);
        return "/test/board/list";
    }

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
    @GetMapping({"/read", "/modify"})
    public String read(HttpServletRequest request, Long bno, PageRequestDTO pageRequestDTO, Model model){

        String requestURI = request.getRequestURI();

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info("get-read:" + pageRequestDTO);

        model.addAttribute("dto", boardDTO);

        log.info("/test" + requestURI);
        return "/test" + requestURI;
    }
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

    @PostMapping("/remove")
    public String remove( Long bno, RedirectAttributes redirectAttributes){
        log.info("remove......post: " + bno);

        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/list";
    }



}
