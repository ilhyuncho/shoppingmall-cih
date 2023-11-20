package com.cih.shoppingmallcih.controller.test.RestfulVersioning;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.service.test.CourceService;
import com.cih.shoppingmallcih.service.test.ModernCourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cources1")
@RequiredArgsConstructor
public class CustomHeaderCourceController {
    private final CourceService courceService;

    private final ModernCourceService modernCourceService;

    @GetMapping(headers = "X-API-VERSION=v1")    // HTTP 해더를 사용
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Cource> getAllLegacyCources(){
        return courceService.getCources();
    }

    @GetMapping(headers = "X-API-VERSION=v2")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Cource> getAllModernCources1(){
        return modernCourceService.getAvailableCources();   // 임시로
    }
}