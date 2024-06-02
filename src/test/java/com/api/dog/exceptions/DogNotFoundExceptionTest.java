package com.api.dog.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogNotFoundExceptionTest {

    @Test
    public void testDogNotFoundExceptionMessage() {
        Long id = 1L;
        String expectedMessage = "Dog with id 1 wasn't found.";

        DogNotFoundException exception = new DogNotFoundException(id);

        assertEquals(exception.getMessage(),expectedMessage);
    }
}