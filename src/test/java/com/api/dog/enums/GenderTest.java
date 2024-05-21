package com.api.dog.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void testMaleValue() {
        assertEquals(Gender.MALE, Gender.fromValue("male"));
        assertEquals(Gender.MALE, Gender.fromValue("MALE"));
        assertEquals(Gender.MALE, Gender.fromValue("Male"));
    }

    @Test
    void testFemaleValue() {
        assertEquals(Gender.FEMALE, Gender.fromValue("female"));
        assertEquals(Gender.FEMALE, Gender.fromValue("FEMALE"));
        assertEquals(Gender.FEMALE, Gender.fromValue("Female"));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> Gender.fromValue("unknown"));
        assertThrows(IllegalArgumentException.class, () -> Gender.fromValue(""));
        assertThrows(IllegalArgumentException.class, () -> Gender.fromValue(null));
    }

    @Test
    void testGetValue() {
        assertEquals("male", Gender.MALE.getValue());
        assertEquals("female", Gender.FEMALE.getValue());
    }
}