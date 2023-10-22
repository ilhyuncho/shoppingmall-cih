package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.dto.test.BoardDTO;
import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.dto.test.PageResponseDTO;
import com.cih.shoppingmallcih.service.test.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

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

        log.info("boardDTO");

        Long bno = boardService.register(boardDTO);

        // 쿼리 스트링으로 처리 되지 않음, 브라우저 경로에 보이지 않음
        // 일회성으로 데이터를 전송 할때 사용
        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Long bno, PageRequestDTO pageRequestDTO, Model model){

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);

        return "/test/board/read";
    }

}
