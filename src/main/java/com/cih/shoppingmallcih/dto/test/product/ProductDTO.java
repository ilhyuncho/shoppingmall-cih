package com.cih.shoppingmallcih.dto.test.product;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private String name;
    private Integer price;
    private Integer stock;

}
