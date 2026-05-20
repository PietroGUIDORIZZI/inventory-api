package com.pietro.inventory_api.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFound(
            ItemNotFoundException ex
    ){
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}
