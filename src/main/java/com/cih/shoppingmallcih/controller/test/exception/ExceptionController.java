package com.cih.shoppingmallcih.controller.test.exception;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping
    public void getRuntimeException(){
        throw new RuntimeException("getRunTimeException 호출");
    }
}
