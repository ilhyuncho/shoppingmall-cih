package com.cih.shoppingmallcih.controller.test.validationGroup;


import com.cih.shoppingmallcih.dto.test.validationGroup.ValidatedRequestDto;
import com.cih.shoppingmallcih.dto.test.validationGroup.group.ValidationGroup1;
import com.cih.shoppingmallcih.dto.test.validationGroup.group.ValidationGroup2;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
@Log4j2
public class ValidationController {

    @PostMapping("/validated")
    public ResponseEntity<String> checkValidation
            (@Validated @RequestBody ValidatedRequestDto validatedRequestDto){
        log.error(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }
    @PostMapping("/validated/group1")
    public ResponseEntity<String> checkValidation1
            (   @Validated(ValidationGroup1.class) // ValidationGroup1 지정
                @RequestBody ValidatedRequestDto validatedRequestDto){
        log.error(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }
    @PostMapping("/validated/group2")
    public ResponseEntity<String> checkValidation2
            (   @Validated(ValidationGroup2.class) // ValidationGroup1 지정
                @RequestBody ValidatedRequestDto validatedRequestDto){
        log.error(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("/validated/all-group")
    public ResponseEntity<String> checkValidation3
            (   @Validated({ValidationGroup2.class, ValidationGroup2.class}) // 두개 지정
                @RequestBody ValidatedRequestDto validatedRequestDto){
        log.error(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }


}
