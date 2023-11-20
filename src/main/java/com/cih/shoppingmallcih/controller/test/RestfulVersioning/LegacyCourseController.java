package com.cih.shoppingmallcih.controller.test.RestfulVersioning;


import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.service.test.CourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cources/v1")
public class LegacyCourseController {

    private final CourceService courceService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Cource> getAllCourses(){
        return courceService.getCources();
    }

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Cource createCource(@Valid @RequestBody Cource cource){
        return courceService.createCource(cource);
    }
}
