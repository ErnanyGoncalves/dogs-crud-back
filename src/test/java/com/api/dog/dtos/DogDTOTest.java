package com.api.dog.dtos;

import com.api.dog.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogDTOTest {


    @Test
    public void testDogDTO(){
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        DogDTO dog = new DogDTO(1L, "Dog name", 1, Gender.MALE, "Dog breed", 5F, 10F, photoUrl, "Some text");

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
}