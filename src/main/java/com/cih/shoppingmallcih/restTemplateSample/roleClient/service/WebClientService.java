package com.cih.shoppingmallcih.restTemplateSample.roleClient.service;

import com.cih.shoppingmallcih.controller.restTemplateForServer.MemberDTO;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class WebClientService {
    public String getName(){
        // builder를 활요해 WebClient 생성
        // WebClient는 우선 객체를 생성한 후 요청을 전달하는 방식

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // 일단 build된 WebClient는 변경할수 없다

        // 실제 요청 코드
        return webClient.get()
                .uri("/restTemplate/v1/crud-api")
                .retrieve() // 요청에 대한 응답을 받았을때 그 값을 추출하는 방법 중 하나
                .bodyToMono(String.class)       // 리턴 타입을 설정해서 문자열 객체를 받아오게 됨
                .block();       // WebClient는 기본적으로 논블로킹 방식으로 동작하기 때문에 바꿔줌
    }

    public String getNameWithPathVariable(){
        // create를 활요해 WebClient 생성
        WebClient webClient = WebClient.create("http://localhost:8080");

        ResponseEntity<String> responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/restTemplate/vi/crud-api/{name}")
                        .build("Flature"))  // 추가할 값
                        .retrieve().
                toEntity(String.class).block(); // toEntity를 사용하면 ResponseEntity타입으로 응답을 전달 받을수 있음

        return responseEntity.getBody();
    }

    public String getNameWithParameter(){
        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.get().uri(uriBuilder -> uriBuilder.path("/restTemplate/v1/crud-api")
                .queryParam("name", "Flatuer")
                .build())
                .exchangeToMono(clientResponse -> { // exchange는 응답 결과 코드에 따라 다르게 응답을 설정할수 있음
                    if(clientResponse.statusCode().equals(HttpStatus.OK)){
                        return clientResponse.bodyToMono(String.class);
                    }else{
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    public ResponseEntity<MemberDTO> postWithParamAndBody(){
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("flature");
        memberDTO.setEmail("ci1@naver.com");
        memberDTO.setOrganization("Around fsdf sdf");

        return webClient.post().uri(uriBuilder -> uriBuilder.path("/restTemplate/v1/crud-api")
                .queryParam("name","fdff")
                .queryParam("email", "cih2@fgdfgdfg")
                .queryParam("organization", "Wikidfdf")
                .build())
                .bodyValue(memberDTO)
                .retrieve()
                .toEntity(MemberDTO.class)
                .block();
    }

    public ResponseEntity<MemberDTO> postWithHeader(){
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("flature");
        memberDTO.setEmail("ci1@naver.com");
        memberDTO.setOrganization("Around fsdf sdf");

        return webClient.post().uri(uriBuilder -> uriBuilder.path("/restTemplate/v1/crud-api/add-header")
                        .build())
                .bodyValue(memberDTO)
                .header("my-header", "widkfddsdf")
                .retrieve()
                .toEntity(MemberDTO.class)
                .block();
    }



}
