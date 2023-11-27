package com.cih.shoppingmallcih.dto.test.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private Integer price;
    private Integer stock;

}
