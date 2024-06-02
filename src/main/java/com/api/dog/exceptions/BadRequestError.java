package com.api.dog.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class BadRequestError {
    private Instant timestamp;
    private Integer status;
    private String message;
    private List<BadRequestErrorDetail> errors;
    private String path;
}




