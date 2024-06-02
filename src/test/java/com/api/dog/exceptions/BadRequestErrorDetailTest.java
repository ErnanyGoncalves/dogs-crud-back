package com.api.dog.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestErrorDetailTest {

    @Test
    public void testBadRequestErrorDetail() {
        String message = "Invalid input";
        String objectName = "user";
        String field = "email";

        BadRequestErrorDetail errorDetail = new BadRequestErrorDetail(message, objectName, field);

        assertEquals(errorDetail.getMessage(),message);
        assertEquals(errorDetail.getObjectName(),objectName);
        assertEquals(errorDetail.getField(),field);
    }
}