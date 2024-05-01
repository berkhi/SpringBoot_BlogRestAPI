package com.berkhayta.springbootblogrestapi.exceptions;

// Bu sınıf tüm controller sınıfları için merkezi bir şekilde hata yönetimi sağlayacaktır.

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorMessage> handleDemoException(BlogAppException ex){
        ErrorType errorType = ex.getErrorType();
        return new ResponseEntity(createErrorMessage(ex),errorType.getStatus());
    }

    private ErrorMessage createErrorMessage(BlogAppException ex)
    {
       return ErrorMessage.builder().code(ex.getErrorType().getCode()).message(ex.getMessage()).build();
    }


    @ExceptionHandler(RuntimeException.class) // Hata yakalayici bir metod olduğunu belirtmek için.
    public ResponseEntity<String> handleException(RuntimeException ex){
       return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
