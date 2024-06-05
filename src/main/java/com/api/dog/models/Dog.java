package com.api.dog.models;

import com.api.dog.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Entity
@Data
@NoArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Field name is required.")
    @Size(max= 12,message = "Field name can not have more than 12 characters.")
    private String name;
    @NotNull(message = "Field age is required.")
    @Min(value = 0, message = "The minimum age is 0 years.")
    @Max(value = 20, message = "The maximum age is 20 years.")
    private Integer age;
    @NotNull(message = "Field gender is required.")
    private Gender gender;
    @NotEmpty(message = "Field breed is required.")
    @Size(max= 31,message = "Field breed can not have more than 31 characters.")
    private String breed;
    @DecimalMin(value = "0.1", message = "The minimum height is 0.1 cm.")
    @DecimalMax(value = "112", message = "The minimum height is 112 cm.")
    @NotNull(message = "Field height is required.")
    private Float height;
    @DecimalMin(value = "0.1", message = "The minimum weight is 0.1 kg.")
    @DecimalMax(value = "156", message = "The minimum weight is 156 kg.")
    @NotNull(message = "Field weight is required.")
    private Float weight;
    @Pattern(regexp = "https?://.*\\.(?:png|jpg)|(https://placehold.co/500)", message = "Invalid URL.")
    private String photo = "https://placehold.co/500";
    @Column(columnDefinition = "TEXT")
    @Size(max= 500,message = "Field about can not have more than 500 characters.")
    private String about;

    public Dog(Long id, String name, Integer age, Gender gender, String breed, Float height, Float weight, String photo, String about) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.height = height;
        this.weight = weight;
        Optional.ofNullable(photo)
                .filter(Predicate.not(String::isBlank))
                .ifPresent(img -> this.photo = img);
        this.about = about;
    }
}
