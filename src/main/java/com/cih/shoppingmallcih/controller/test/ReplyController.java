package com.cih.shoppingmallcih.controller.test;


import com.cih.shoppingmallcih.dto.test.ReplyDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {


//    @ApiOperation(value="Replies POST", notes="POST 방식으로 댓글 등록")
//    //consumes는 클라이언트가 서버에게 보내는 데이터 타입을 명시한다.
//    //produces는 서버가 클라이언트에게 반환하는 데이터 타입을 명시한다.
//    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO){
//        // @RequestBody : JSON문자열을 ReplyDTO로 변환
//
//        log.info(replyDTO);
//
//        Map<String, Long> resultMap = Map.of("rno", 111L);
//
//        // @ResponseBody는 데이터를 반환할때 자바 객체를 응답 본문(body)메시지를 만들어 반환 할수 있게 해줌
//        // @RestController를 사용하면 자동으로 모든 핸들러 메소드에 @ResponseBody가 적용됨
//        // @ResponseEntity는 반환값에 상태코드와 응답 메시지를 주고 싶을 때 주로 사용
//        return ResponseEntity.ok(resultMap);
//    }

    @ApiOperation(value="Replies POST", notes="POST 방식으로 댓글 등록")
    //consumes는 클라이언트가 서버에게 보내는 데이터 타입을 명시한다.
    //produces는 서버가 클라이언트에게 반환하는 데이터 타입을 명시한다.
    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO,
                                      BindingResult bindingResult) throws BindException{
        // @RequestBody : JSON문자열을 ReplyDTO로 변환

        log.info(replyDTO);

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", 111L);

        return ResponseEntity.ok(resultMap);

    }




}
