package com.api.dog.dtos;


import com.api.dog.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DogDTO {
    private final Long id;
    private final String name;
    private final Integer age;
    private final Gender gender;
    private final String breed;
    private final Float height;
    private final Float weight;
    private final String photo;
    private final String about;
}
