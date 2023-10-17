package com.cih.shoppingmallcih.controller.test;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
@Log4j2
public class TestRestController {

    @GetMapping("/String")
    public String StringTest(){
        return "Hello...........dfsdfsdf";
    }


}
