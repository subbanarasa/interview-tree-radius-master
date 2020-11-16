package com.holidu.interview.assignment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String UNKNOWN_EXCEPTION_CD= "100";
    private static final String RUNTIME_EXCEPTION_CD= "101";

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(final Exception exception) {
        return new ResponseEntity<Object>(
                new ExceptionInfo(UNKNOWN_EXCEPTION_CD, exception.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException runtimeException) {

        return new ResponseEntity<Object>(
                new ExceptionInfo(RUNTIME_EXCEPTION_CD, runtimeException.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
