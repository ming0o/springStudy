package com.study.spring.controller;

import com.study.spring.dto.response.ErrorResponse;
import com.study.spring.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError error :e.getFieldErrors()) {
            response.addValidation(error.getField(), error.getDefaultMessage());
        }
        return response;
    }

    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalEx(GlobalException e) {
        int statusCode = e.getStatusCode(); // 단축키 커맨드 옵션 v

        ErrorResponse response = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message((e.getMessage()))
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }
}
