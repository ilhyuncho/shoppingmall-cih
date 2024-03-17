package com.cih.shoppingmallcih.controller.test.regEx;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
        } else{
            return ResponseEntity.badRequest().body(Map.of("error", "invalid String"));
        }
        return ResponseEntity.ok(resultMap);
    }
}
