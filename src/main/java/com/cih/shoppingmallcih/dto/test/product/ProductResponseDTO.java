package com.cih.shoppingmallcih.dto.test.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long number;
    private String name;
    private Integer price;
    private Integer stock;
}
