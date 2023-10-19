package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.service.test.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model){
        log.info("board   list.......");

        return "/test/board/list";

    }

}
