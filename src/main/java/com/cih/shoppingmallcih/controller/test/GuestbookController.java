package com.cih.shoppingmallcih.controller.test;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.dto.typeCommand.GuestBookType;
import com.cih.shoppingmallcih.service.test.GuestbookService;
import com.cih.shoppingmallcih.service.test.mybatisTest.MybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {
    private final MybatisService mybatisService;

    private final GuestbookService guestbookService;

    public static final String GUEST_GOLD = "GOLD";
    public static final String GUEST_SILVER = "SILVER";
    public static final String GUEST_BRONZE = "BRONZE";
    public static final GuestBookType GUEST_GOLD_NEW = new GuestBookType("GOLD");
    public static final GuestBookType GUEST_SILVER_NEW = new GuestBookType("SILVER");
    public static final GuestBookType GUEST_BRONZE_NEW = new GuestBookType("BRONZE");


    @GetMapping({"/", "/list"})
    public String list(){
        log.info("list.......");

        return "/test/guestbook/list";
    }

    @GetMapping( "/register")
    public String register(){
        log.info("register.......");

        return "/test/guestbook/register";
    }
    @PostMapping("/register")
    //@ApiImplicitParam(name="guestType", value= "타입",  required = true)
    public String registerGuestbook(@Valid GuestbookDTO dto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info("Guestbook POST register......");

        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/guestbook/register";
        }

        log.info("GuestbookDTO" + dto);

        // 임시로
        dto.setModDate(LocalDateTime.now());
        dto.setRegDate(LocalDateTime.now());


        mybatisService.insertGuestBook(dto);

        log.error("getGuestType: " + dto.getGuestType());   // getGuestType: BRONZE
        log.error("getGuestType.getName(): " + dto.getGuestType().getName());   // 브론즈

        guestbookService.testUserType(GUEST_GOLD);
        guestbookService.testUserType("상관없는 스트링을 넣어도 동작");
        // 개선 된 버전
        guestbookService.testUserTypeNew(GUEST_GOLD_NEW);

        return "redirect:/guestbook/list";
    }

}
