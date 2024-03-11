package com.cih.shoppingmallcih.dto.test.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString   // 24.03.11 추가
public class ProductResponseDTO {

    private Long number;
    private String name;
    private Integer price;
    private Integer stock;
}
