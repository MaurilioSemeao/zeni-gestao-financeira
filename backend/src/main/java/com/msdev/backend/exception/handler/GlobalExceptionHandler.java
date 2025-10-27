package com.msdev.backend.exception.handler;

import com.msdev.backend.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleCartaoException(BusinessException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro relacionado ao Cartão", ex.getMessage());
    }



    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);

        return ResponseEntity.status(status).body(response);
    }
}
