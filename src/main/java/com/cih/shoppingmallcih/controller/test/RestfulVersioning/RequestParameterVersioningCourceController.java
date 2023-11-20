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
@RequestMapping("/cources")
@RequiredArgsConstructor
public class RequestParameterVersioningCourceController {
    private final CourceService courceService;              // v1 용

    private final ModernCourceService modernCourceService;  // v2 용

    @GetMapping(value="/path", params="version=v1")    // API 호출 시 파라미터로 version=v1을 지정해야 한다
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Cource> getAllLegacyCources(){
        return courceService.getCources();
    }

    @GetMapping(value="/path",params="version=v2")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Cource> getAllModernCources1(){
        return modernCourceService.getAvailableCources();   // 임시로
    }
}
