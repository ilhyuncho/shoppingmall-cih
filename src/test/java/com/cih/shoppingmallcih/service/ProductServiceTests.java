package com.cih.shoppingmallcih.service;


import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.ProductRepository;
import com.cih.shoppingmallcih.dto.test.product.ProductDTO;
import com.cih.shoppingmallcih.dto.test.product.ProductResponseDTO;
import com.cih.shoppingmallcih.service.test.ProductService;
import com.cih.shoppingmallcih.service.test.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)  // 스프링에서 객체를 주입받기위해. Spring TestContext Framework와 Junit5와 통합하여 사용
@Import({ProductServiceImpl.class}) // @Autowired로 주입받는 ProductService를 주입 받기 위해
public class ProductServiceTests {

    //private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    @MockBean       // 스프링에 mock객체를 등록해서 주입받는 형식
    ProductRepository productRepository;

    //private ProductServiceImpl productService;
    @Autowired
    ProductService productService;

    private final ModelMapper modelMapper = new ModelMapper();


//    @BeforeEach
//    public void setUpTest(){
//        productService = new ProductServiceImpl(productRepository ,modelMapper);
//    }

    @Test
    void getProductTest(){
        Product givenProduct = new Product();
        givenProduct.setNumber(2L);
        givenProduct.setName("chnage21");
        givenProduct.setPrice(1000);
        givenProduct.setStock(5);

        // when,  Mockito에선 when 메소드를 이용해서 스터빙을 지원
        Mockito.when(productRepository.findById(2L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDTO productResponseDTO = productService.getProduct(2L);

        Assertions.assertEquals(productResponseDTO.getNumber(),givenProduct.getNumber());
        Assertions.assertEquals(productResponseDTO.getName(),givenProduct.getName());
        Assertions.assertEquals(productResponseDTO.getPrice(),givenProduct.getPrice());
        Assertions.assertEquals(productResponseDTO.getStock(),givenProduct.getStock());

        // verify
        verify(productRepository,only()).findById(2L);
    }

    @Test
    void saveProductTest(){
        
        Mockito.when(productRepository.save(any(Product.class)))    // any는 검증단계에서 메서드의 실행만을 확인 하거나, 좀 더 큰 범위의 클래스 객체를 매개변수로 
                                                                    // 전달 받는 등의 상황에서 사용
                .then(AdditionalAnswers.returnsFirstArg());     // returnsFirstArg 전달한 값을 그대로 반환

        ProductResponseDTO productResponseDTO = productService.saveProduct(new ProductDTO("펜1", 1000, 123));

        Assertions.assertEquals(productResponseDTO.getName(),"펜1");
        Assertions.assertEquals(productResponseDTO.getPrice(),1000);
        Assertions.assertEquals(productResponseDTO.getStock(),123);

        verify(productRepository).save(any());
    }






}
