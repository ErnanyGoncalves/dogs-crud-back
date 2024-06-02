package com.api.dog.exceptions;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class StandardErrorTest {
    @Test
    public void testStandardError() {
        Instant timestamp = Instant.now();
        Integer status = 404;
        String error = "Not Found";
        String message = "Resource not found";
        String path = "/api/resource/123";

        StandardError standardError = new StandardError(timestamp, status, error, message, path);

        assertEquals(standardError.getTimestamp(),timestamp);
        assertEquals(standardError.getStatus(),status);
        assertEquals(standardError.getError(),error);
        assertEquals(standardError.getMessage(),message);
        assertEquals(standardError.getPath(),path);
    }
}