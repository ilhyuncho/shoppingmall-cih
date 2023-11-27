package com.cih.shoppingmallcih.dto.test.product;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeProductNameDto {
    private Long number;
    private String name;

    public ChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }
}
