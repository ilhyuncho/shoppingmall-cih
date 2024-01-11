package com.cih.shoppingmallcih.controller.test.thymeleaf;

import com.cih.shoppingmallcih.dto.test.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/test/thymeleaf")
@Log4j2
public class SampleController {

    @GetMapping("/ex1")
    public void ex1(Model model){

        List<String> list = Arrays.asList("aaa","bbb","ccc");
        model.addAttribute("list", list);
        log.info("ex1.........");
    }

    @GetMapping( {"/ex2", "/exLink"} )   // {} 여러개 지정
    public void ex2(Model model){

        List<SampleDTO> list= IntStream.rangeClosed(1,20).asLongStream().mapToObj(i -> {
            SampleDTO dto = SampleDTO.builder()
                    .sno(i)
                    .first("First,," + i)
                    .last("Last.." + i)
                    .regTime(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping("/exInline")
    public String exInline(RedirectAttributes redirectAttributes){

        log.info("exInline......");

        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("First...100")
                .last("Last...100")
                .regTime(LocalDateTime.now())
                .build();

        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/test/thymeleaf/ex3";
    }

    @GetMapping("/ex3")
    public void ex3(){
        log.info("ex3");
    }

    @GetMapping({"/exView"})
    public void exView(String sno, Model model){
        log.info("exView....: " + sno );

        model.addAttribute("sno", sno);
    }

    @GetMapping({"/exView/*"})
    public String exView2(RedirectAttributes redirectAttributes){
        //    sno를 path로 지정했을때 받는 방법

        log.info("exView2....: ");

        redirectAttributes.addFlashAttribute("result", "success~~~~~");

        return "redirect:/test/thymeleaf/ex3";
    }

    @GetMapping({"/thymeleafEx"})
    public void thymeleafEx(){

//        thymeleaf 의 layout:decorate 활용 예제

        log.info("thymeleafEx......");
    }

    @GetMapping({"/exTemplate"})
    public void exTemplate(){
        log.info("exTemplate......");
    }
}
