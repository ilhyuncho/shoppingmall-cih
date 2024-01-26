package com.cih.shoppingmallcih.dto.test.validationGroup;


import com.cih.shoppingmallcih.dto.test.validationGroup.group.ValidationGroup1;
import com.cih.shoppingmallcih.dto.test.validationGroup.group.ValidationGroup2;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidatedRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phoneNumber;

    @Min(value = 20, groups= ValidationGroup1.class)
    @Max(value = 40, groups= ValidationGroup1.class)
    private int age;

    @Size(min =0, max = 40)
    private String description;

    @Positive(groups = ValidationGroup2.class)
    private int count;

    @AssertTrue
    private boolean booleanCheck;

}
