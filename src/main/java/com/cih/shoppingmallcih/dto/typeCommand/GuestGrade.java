package com.cih.shoppingmallcih.dto.typeCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuestGrade {
    GOLD("골드"),
    SILVER("실버"),
    BRONZE("브론즈"),

    ;
    private final String name;
}
