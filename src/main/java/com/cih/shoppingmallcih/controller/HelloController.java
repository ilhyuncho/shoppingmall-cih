package com.cih.shoppingmallcih.controller;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public void hello(){
        // test/hello.html 으로 이동
    }




}
