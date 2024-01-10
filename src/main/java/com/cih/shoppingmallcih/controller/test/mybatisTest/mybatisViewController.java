package com.cih.shoppingmallcih.controller.test.mybatisTest;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.service.test.mybatisTest.MybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mybatis")
@RequiredArgsConstructor
@Log4j2
public class mybatisViewController {

    private final MybatisService mybatisService;

    @GetMapping("/list")
    public String list(Model model) {
        log.info("guestbook1 list.....");
        List<GuestbookDTO> result = mybatisService.getAll();

        result.forEach(System.out::println);

        model.addAttribute("dtoList", result);

        return "/test/guestbook/list2";
    }

    @GetMapping({"/read", "/modify"})
    public String read(HttpServletRequest request, Long gno, Model model) {

        String requestURI = request.getRequestURI();
        log.info("gno : " + gno);

        GuestbookDTO dto = mybatisService.getOne(gno);
        log.info("dto : " + dto);

        model.addAttribute("dto", dto);

        //requestURI = mybatis/read
        int target_num = requestURI.lastIndexOf("/");
        String result = requestURI.substring(target_num);

        return "/test/guestbook" + result;
    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        log.info("---------remove--------------");
        log.info("gno: " + gno);

        mybatisService.remove(gno);

        return "redirect:/mybatis/list";
    }
    @PostMapping("/modify")
    public String modify(@Valid GuestbookDTO guestbookDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        log.info("mybatis modify()..........");

        log.info(guestbookDTO);


        if (bindingResult.hasErrors()) {
            log.info("has errors..........");

            List<ObjectError> error = bindingResult.getAllErrors();

            for (ObjectError objectError : error) {
                log.info( "errors.........." +objectError.getCode());
                log.info( "errors.........." +objectError.getObjectName());

            }
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("gno", guestbookDTO.getGno());

            return "redirect:/mybatis/modify";
        }

        log.info(guestbookDTO);

        mybatisService.modify(guestbookDTO);

        return "redirect:/mybatis/list";
    }

}
