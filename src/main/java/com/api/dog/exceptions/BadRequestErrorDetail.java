package com.api.dog.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BadRequestErrorDetail {
    private String message;
    private String objectName;
    private String field;
}
