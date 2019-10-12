package com.vulenhtho.mobileboot.controller;

import com.vulenhtho.mobileboot.model.respone.DataResponse;
import com.vulenhtho.mobileboot.util.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Validated
public class BaseController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String code = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String message = ErrorCode.getMessage(code);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(code);
        dataResponse.setMessage(message);
        return ResponseEntity.ok(dataResponse);

    }

}
