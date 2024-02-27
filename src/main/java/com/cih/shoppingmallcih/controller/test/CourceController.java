package com.cih.shoppingmallcih.controller.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.service.test.CourceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cources")
@RequiredArgsConstructor
@Log4j2
public class CourceController {

    private final CourceService courceService;

    @GetMapping                              //아무 경로가 지정되지 않았으므로 클래스에서 @RequestMapping으로
                                    // 지정한 기본 경로인 /cources/로 들어오는 모든 GET요청은 이 메서드로 전달 됨
    @ResponseStatus(code = HttpStatus.OK)       // 앤드포인트가 반환하는 HTTP 상태 코드
    @Operation(summary="앤드포인트의 목적을 설명")
    public Iterable<Cource> getAllCources(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate fromDate,
            @RequestParam(value="toDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate toDate
            ){

        if(Objects.nonNull(fromDate) && Objects.nonNull(toDate)){
            log.info("fromDate" + fromDate);
            log.info("toDate" + toDate);
        }


        return courceService.getCources();
    }

    @GetMapping("{id}")
    public Optional<Cource> getCourceId(@PathVariable("id") long courceId){
        return courceService.getCourceById(courceId);
    }

    @GetMapping("category/{name}")
    public Iterable<Cource> getCourceByCategory(@PathVariable("name") String category){
        return courceService.getCourcesByCategory(category);
    }

    @PostMapping
    public Cource createCource(@RequestBody Cource cource){
        // 전달된 json을 자바 POJO객체로 역직렬화하는 일은 스프링 부트가 자동으로 수행해준다.

        return courceService.createCource(cource);
    }

    @PutMapping("{id}")
    public void updateCource(@PathVariable("id") Long courceId, @RequestBody Cource cource){
        courceService.updateCource(courceId, cource);
    }

    @DeleteMapping("{id}")
    void deleteCourceById(@PathVariable("id") Long courceId){
        courceService.deleteCourceById(courceId);
    }

    @DeleteMapping
    void deleteCources(){
        courceService.deleteCources();
    }

}
