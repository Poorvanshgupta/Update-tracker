package com.updatetracker.update_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<?> notFound(ResourceNotFoundException e){return body(HttpStatus.NOT_FOUND,e.getMessage());}
    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<?> bad(BadRequestException e){return body(HttpStatus.BAD_REQUEST,e.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> validation(MethodArgumentNotValidException e){
        Map<String,String> errors=new LinkedHashMap<>();
        for(FieldError f:e.getBindingResult().getFieldErrors()) errors.put(f.getField(),f.getDefaultMessage());
        return ResponseEntity.badRequest().body(Map.of("timestamp",LocalDateTime.now(),"errors",errors));
    }
    private ResponseEntity<?> body(HttpStatus s,String msg){return ResponseEntity.status(s).body(Map.of("timestamp",LocalDateTime.now(),"message",msg));}
}
