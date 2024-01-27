package com.cih.shoppingmallcih.restTemplateSample.roleClient.service;

import com.cih.shoppingmallcih.controller.restTemplateForServer.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Log4j2
public class RestTemplateService {

    public String getName(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/restTemplate/v1/crud-api")
                .encode()                   // 인코딩 문자셋 ( 전달하지 않ㅇ면 기본적으로 UTF-8 )
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        log.info(responseEntity.getBody());

        return responseEntity.getBody();
    }

    public String getNameWithPathVariable(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")

                .path("/restTemplate/v1/crud-api/{name}")

                .encode()                   // 인코딩 문자셋 ( 전달하지 않ㅇ면 기본적으로 UTF-8 )
                .build()
                .expand("Flature")  // 복수의 값을 넣어야할 경우 , 를 추가하여 구분
                .toUri();       // URI 타입으로 리턴 받음

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        log.info(responseEntity.getBody());
        return responseEntity.getBody();
    }

    public String getNameWithParameter(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/restTemplate/v1/crud-api/param")

                .queryParam("name", "Flature")

                .encode()                   // 인코딩 문자셋 ( 전달하지 않ㅇ면 기본적으로 UTF-8 )
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public ResponseEntity<MemberDTO> postWithParamAndBody(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/restTemplate/v1/crud-api")
                .queryParam("name", "Flature")
                .queryParam("email","cihg1@naver.com")
                .queryParam("organization","fsdfggfg")
                .encode()
                .build()
                .toUri();

        // RequestBody에 값을 담는다
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("flature");
        memberDTO.setEmail("ci1@naver.com");
        memberDTO.setOrganization("Around fsdf sdf");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        return responseEntity;
    }

    public ResponseEntity<MemberDTO> postWithHeader(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/restTemplate/v1/crud-api/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("flature");
        memberDTO.setEmail("ci1@naver.com");
        memberDTO.setOrganization("Around fsdf sdf");

        RequestEntity<MemberDTO> requestEntity = RequestEntity.post(uri)
                .header("my-header", "kwidj api")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

        return responseEntity;
    }


}
