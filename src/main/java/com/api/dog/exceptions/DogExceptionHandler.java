package com.api.dog.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class DogExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DogNotFoundException.class)
    public ResponseEntity<StandardError> dogNotFound(DogNotFoundException e, HttpServletRequest request) {
        String error = "Dog not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result = ex.getBindingResult();

        List<BadRequestErrorDetail> errors = result.getFieldErrors().stream()
                .map(fieldError -> new BadRequestErrorDetail(
                        fieldError.getDefaultMessage(),
                        fieldError.getObjectName(),
                        fieldError.getField()
                ))
                .collect(Collectors.toList());

        BadRequestError response = new BadRequestError(Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors,
                request.getDescription(false)
        );

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerException(Exception ex,
                                                                WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                              WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                 WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause instanceof IllegalArgumentException) {
            return handleIllegalArgumentException((IllegalArgumentException) mostSpecificCause, request);
        }
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        if (Objects.isNull(body) && !(body instanceof BadRequestError)) {
            body = obtainResponse(statusCode, ex, request);
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


    private StandardError obtainResponse(HttpStatusCode status, Exception ex,
                                         WebRequest request) {

        final HttpStatusCode newStatus = Optional.ofNullable(status)
                .orElse(HttpStatusCode.valueOf(500));
        final String message = HttpStatus.valueOf(newStatus.value()).getReasonPhrase();

        return new StandardError(Instant.now(), newStatus.value(), ex.getMessage(), message,
                request.getDescription(false));
    }

}
