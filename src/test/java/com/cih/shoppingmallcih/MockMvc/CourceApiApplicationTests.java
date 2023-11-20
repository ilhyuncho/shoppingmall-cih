package com.cih.shoppingmallcih.MockMvc;


import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.dto.test.Validation.Course;
import com.cih.shoppingmallcih.service.test.CourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc   // MockMVC프레임워크가 자동 구성되어 편리하게 MockMVc인스턴스를 주입 받아서 테스트 할수 있다.
@ExtendWith(SpringExtension.class)  // JUnit5의 주피터 프로그래밍 모델과 스프링 테스트 컨텍스트 프레임워크를 함께 테스트에 사용할 수 있다
public class CourceApiApplicationTests {

    @Autowired
    private CourceService courceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostCource() throws Exception {
        Cource cource = Cource.builder().name("Padfsgdfg")
                .category("Spring")
                .rating(5)
                .description("gdfgexdfsdfsdfsdf").build();

        ObjectMapper objectMapper = new ObjectMapper(); // Cource인스턴스를 Json으로 직렬화 할때 사용

        MockHttpServletResponse response = mockMvc.perform(post("/cources/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cource)))
                .andDo(print())     // 테스트 진행 과정을 콘솔에 출력 함
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Padfsgdfg"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
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
