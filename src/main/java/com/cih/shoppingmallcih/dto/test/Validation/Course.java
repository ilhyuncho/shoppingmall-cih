package com.cih.shoppingmallcih.dto.test.Validation;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Course {

    private long id;

    @NotBlank
    private String name;
    private String Category;

    @Min(value=1, message = "A course sould have minimum of 1 rating")
    @Max(value=5, message = "A course sould have maximum of 5 rating")
    private int rating;

    private String description;
}
