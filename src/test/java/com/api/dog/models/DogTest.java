package com.api.dog.models;

import com.api.dog.enums.Gender;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DogTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testDogWithPhotoUrl() {
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        Dog dog = new Dog(1L, "Dog name", 1, Gender.MALE, "Dog breed", 5F, 10F, photoUrl, "Some text");
        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(0, violations.size());

        assertEquals(1L, dog.getId());
        assertEquals("Dog name", dog.getName());
        assertEquals(1, dog.getAge());
        assertEquals(Gender.MALE, dog.getGender());
        assertEquals("Dog breed", dog.getBreed());
        assertEquals(5F, dog.getHeight());
        assertEquals(10F, dog.getWeight());
        assertEquals(photoUrl, dog.getPhoto());
        assertEquals("Some text", dog.getAbout());
    }

    @Test
    void testDogWithoutPhotoUrlAndAbout() {
        String defaultPhotoUrl = "https://placehold.co/500";
        Dog dog = new Dog(1L, "Dog name", 1, Gender.MALE, "Dog breed", 5F, 10F, null, null);
        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(0, violations.size());
        assertEquals(defaultPhotoUrl, dog.getPhoto());
        assertNull(dog.getAbout());
    }

    @Test
    void testDogWithEmptyErrors() {
        String defaultPhotoUrl = "https://placehold.co/500";
        Dog dog = new Dog();
        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(6, violations.size());
        assertEquals(defaultPhotoUrl, dog.getPhoto());
        assertNull(dog.getAbout());
    }

    @Test
    void testDogWithErrorsInAgeHeightWeight() {
        Dog dog = new Dog(1L, "Dog name", -1, Gender.MALE, "Dog breed", 0F, 0F, null, null);

        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(3, violations.size());
    }

    @Test
    void testDogWithErrorsInAge() {
        Dog dog = new Dog(1L, "Dog name", 60, Gender.MALE, "Dog breed", 5F, 10F, null, null);

        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(1, violations.size());
    }

    @Test
    void testDogWithInvalidUrl() {
        String photoUrl = "https://www.google.com/";
        Dog dog = new Dog(1L, "Dog name", 1, Gender.MALE, "Dog breed", 5F, 10F, photoUrl, "Some text");
        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(1, violations.size());
    }

    @Test
    void testDogWithLongNameAndBreed() {
        Dog dog = new Dog(1L, "A very long name", 1, Gender.MALE, "A very long breed in this field here", 5F, 10F, null, "Some text");
        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(2, violations.size());
    }
    @Test
    void testDogWithErrorsInHeightWeightAbout() {
        String longAbout = "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii";
        Dog dog = new Dog(1L, "Dog name", 1, Gender.MALE, "Dog breed", 500F, 500F, null, longAbout);

        Set<ConstraintViolation<Dog>> violations = validator.validate(dog);
        assertEquals(3, violations.size());
    }
}