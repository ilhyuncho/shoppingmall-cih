package com.cih.shoppingmallcih.controller.test;

import com.cih.shoppingmallcih.dto.test.TestRequestDTO;
import com.cih.shoppingmallcih.dto.test.TestResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/test")
@Log4j2
public class HelloController {

    @GetMapping("/hello")
    public void hello(){
        // test/hello.html 으로 이동

        log.info("fdfdfdfdf");
        log.error("erordfsdfsdfs");

    }

    @GetMapping("/list")
    public void list(TestRequestDTO testDTO, Model model){

        List<TestResponseDTO> dto = new ArrayList<>();
        dto.add(new TestResponseDTO("fdf",20));
        dto.add(new TestResponseDTO("fdf1",30));

        model.addAttribute("TestResponseDTO",dto );


    }





}
