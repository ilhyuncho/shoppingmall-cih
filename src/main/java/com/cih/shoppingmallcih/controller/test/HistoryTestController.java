package com.cih.shoppingmallcih.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/history")
public class HistoryTestController {

    @GetMapping("/historyTest")
    public void historyTest(){

    }


}
