package com.productservice.productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.productservice.productservice.dto.ExceptionDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    private ExceptionDto handleProductNotFoundException(
            ProductNotFoundException productNotFoundException
        ){
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
            exceptionDto.setMessage(productNotFoundException.getMessage());
            return exceptionDto;
        }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    private ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundException(
        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        {
            return null;
        }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ExceptionDto> handleNullPointerExcption(
        NullPointerException nullPointerException)
    {
        return null;
    }

}

