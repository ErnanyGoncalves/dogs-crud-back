package com.api.dog.exceptions;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestErrorTest {

    @Test
    public void testBadRequestErrorInitialization() {
        Instant timestamp = Instant.now();
        Integer status = 400;
        String message = "Bad request";
        String path = "/test";
        List<BadRequestErrorDetail> errors = new ArrayList<>();

        errors.add(new BadRequestErrorDetail("Invalid input", "user", "email"));

        BadRequestError badRequestError = new BadRequestError(timestamp, status, message, errors,path);

        assertEquals(badRequestError.getTimestamp(),timestamp);
        assertEquals(badRequestError.getStatus(),status);
        assertEquals(badRequestError.getMessage(),message);
        assertEquals(badRequestError.getErrors(),errors);
        assertEquals(badRequestError.getPath(),path);
    }
}