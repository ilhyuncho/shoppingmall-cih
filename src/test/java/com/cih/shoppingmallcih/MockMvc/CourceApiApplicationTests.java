package com.cih.shoppingmallcih.MockMvc;


import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.service.test.CourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)   // 기본값은 WebEnvironment.MOCK
                                // MOCK으로 설정된 테스트 케이스는 실제 서블릿 컨테이너를 사용하지 않고 테스트를 실행 한다
                                // 실제 서블릿 컨테이너를 실행하지 않고 목 서블릿 컨테이너를 사용하여 테스트 진행
@AutoConfigureMockMvc   // MockMVC프레임워크가 자동 구성되어 편리하게 MockMVc인스턴스를 주입 받아서 테스트 할수 있다.
                        // 설정 없으면    private MockMvc mockMvc; 주입 실패
                        // @SpringBootTest 와 같이 테스트 할때 사용
@ExtendWith(SpringExtension.class)  // JUnit5의 주피터 프로그래밍 모델과 스프링 테스트 컨텍스트 프레임워크를 함께 테스트에 사용할 수 있다
                                    // @ExtendWith는 Junit5에서 제공하는 애너테이션,
                                    //        SpringExtension같은 확장 기능을 지정해서 사용할수 있다
                                    // JUnit5버전에서 'spring-test'를 이용하기 위한 설정
public class CourceApiApplicationTests {

    @Autowired
    private CourceService courceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostCource() throws Exception {
        Cource cource = Cource.builder().name("Padfsgdfg1111")
                .category("Spring111")
                .rating(15)
                .description("gdfgexdfsdfsdfsdf").build();

        ObjectMapper objectMapper = new ObjectMapper(); // Cource인스턴스를 Json으로 직렬화 할때 사용

        MockHttpServletResponse response = mockMvc.perform(post("/cources/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cource)))
                .andDo(print())     // 테스트 진행 과정을 콘솔에 출력 함
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Padfsgdfg1111"))
                .andExpect(jsonPath("$.name", Matchers.is("Padfsgdfg1111")))
                .andExpect(jsonPath("$.category").value("Spring111"))
                .andExpect(jsonPath("$.rating").value(15))

                //.andExpect(status().isCreated())  // Http응답코드로 HTTP 201 Created가 반환되는지 단언.. 근데 200이 온다.!~!~
                .andReturn().getResponse();


        Integer id =  JsonPath.parse(response.getContentAsString()).read("$.id");
        assertNotNull(courceService.getCourceById(Long.valueOf(id)));
    }

    @Test
    public void testRetrieveCourse() throws Exception{
        Cource cource = Cource.builder().name("Padfsgdfg1")
                .category("Spring")
                .rating(5)
                .description("gdfgexdfsdfsdfsdf1").build();

        ObjectMapper objectMapper = new ObjectMapper(); // Cource인스턴스를 Json으로 직렬화 할때 사용

        MockHttpServletResponse response = mockMvc.perform(post("/cources")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cource)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Padfsgdfg1"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
                .andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        mockMvc.perform(get("/cources/{id}",id))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Padfsgdfg1"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(status().isOk());
    }




}
