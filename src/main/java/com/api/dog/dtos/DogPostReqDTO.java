package com.api.dog.dtos;


import com.api.dog.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DogPostReqDTO {
    @NotEmpty(message = "Field name is required.")
    @Size(max = 12, message = "Field name can not have more than 12 characters.")
    private String name;
    @NotNull(message = "Field age is required.")
    @Min(value = 0, message = "The minimum age is 0 years.")
    @Max(value = 20, message = "The maximum age is 20 years.")
    private Integer age;
    @NotNull(message = "Field gender is required.")
    private Gender gender;
    @NotEmpty(message = "Field breed is required.")
    @Size(max = 31, message = "Field breed can not have more than 31 characters.")
    private String breed;
    @DecimalMin(value = "0.1", message = "The minimum height is 0.1 cm.")
    @DecimalMax(value = "112", message = "The maximum height is 112 cm.")
    @NotNull(message = "Field height is required.")
    private Float height;
    @DecimalMin(value = "0.1", message = "The minimum weight is 0.1 kg.")
    @DecimalMax(value = "156", message = "The minimum weight is 156 kg.")
    @NotNull(message = "Field weight is required.")
    private Float weight;
    @Column(columnDefinition = "TEXT")
    @Size(max = 500, message = "Field about can not have more than 500 characters.")
    private String about;
}
