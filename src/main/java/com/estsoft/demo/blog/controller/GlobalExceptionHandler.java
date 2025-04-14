package com.estsoft.demo.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
* 전역 예외 처리
* */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Client Error
    // {
    //      "errorCode": 4xx,
    //      "errorMessage": "not exists id=8"
    // }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorHandle> handlerIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorHandle("400", e.getMessage()));
    }
}
