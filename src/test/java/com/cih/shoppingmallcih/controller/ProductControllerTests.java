package com.cih.shoppingmallcih.controller;


import com.cih.shoppingmallcih.controller.test.ProductController;
import com.cih.shoppingmallcih.dto.test.product.ProductDTO;
import com.cih.shoppingmallcih.dto.test.product.ProductResponseDTO;
import com.cih.shoppingmallcih.service.test.ProductServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( controllers = {ProductController.class})   // 대상 클래스만 로드해 테스트, @SpringBootTest보다 가볍게 테스트
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;    // 컨트롤러의 API를 테스트하기 위해 사용된 객체

    @MockBean       // 실제 빈 객첵 아닌 Mock객체를 생성해서 주입하는 역할
    ProductServiceImpl productService;

    @Test
    @WithMockUser   // 401 error (유저 권한 떄문에 실패 를 처리하기 위해 )
    @DisplayName("MOckMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{

        given(productService.getProduct(2L)).willReturn(
                new ProductResponseDTO(2L,"chnage21", 1000,5 ));

        String productId = "2";

        mockMvc.perform(get("/product/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").value(1000))
                .andDo(print());

        verify(productService).getProduct(2L);
    }
    
    @Test
    @WithMockUser   // 401 error (유저 권한 떄문에 실패 를 처리하기 위해 )
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception{
        given(productService.saveProduct(new ProductDTO("pen", 4000, 2000)))
                .willReturn(new ProductResponseDTO(3L, "pen", 4000, 2000));

        ProductDTO productDTO = ProductDTO.builder()
                .name("pen")
                //.price(4000)
                .stock(2000).build();

        ObjectMapper objectMapper = new ObjectMapper(); // ProductDTO 인스턴스를 Json으로 직렬화 할때 사용

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);  // 속성값이 null이면 직렬화 과정에서
                                                                                // 속성을 제외
        String Content = objectMapper.writeValueAsString(productDTO);

        System.out.println(Content.toString()); // {"name":"pen","stock":2000}

        mockMvc.perform(post("/product/save").content(Content)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        System.out.println("sdfsdf--------------------");
        verify(productService).saveProduct(new ProductDTO("pen",4000,2000));



    }
    
}
