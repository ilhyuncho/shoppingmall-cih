package com.cih.shoppingmallcih.controller.test.regEx;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.internal.util.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/regEx")
@Log4j2
@RequiredArgsConstructor
public class RegExController {

    private static Pattern pattern = Pattern.compile("([^=]+)=([^=]+)");

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> testRegEX(@RequestBody String strRex){

        log.error("strRex: " + strRex); // strRex: key.jsonValue=testtestValue

        Map<String, String> resultMap = new HashMap<>();

        Matcher matcher = pattern.matcher(strRex);

        if(matcher.matches()){
            String key = matcher.group(1);
            String value = matcher.group(2);

            resultMap.put(key, value);

            // input : strRex: key.jsonValue=testtestValue
            log.error("key: " + key);   // key.jsonValue
            log.error("value: " + value);   // testtestValue

            // Assert 사용은 조건 체크를 하기 위해 사용
            int a = 3;
            int b = 4;
            Assert.isTrue(isMin(b,a));  // 만약 false 이면 RuntimeException 발생

        } else{
            return ResponseEntity.badRequest().body(Map.of("error", "invalid String"));
        }
        return ResponseEntity.ok(resultMap);
    }

    private boolean isMin(int a, int b){
        return a < b;
    }
}
