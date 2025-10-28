package com.msdev.backend.exception.handler;

import com.msdev.backend.exception.BusinessException;
import com.msdev.backend.exception.transacao.TransacaoInvalidaException;
import com.msdev.backend.exception.usuario.UsuarioInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.MarshalledObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return buildResponse(HttpStatus.BAD_REQUEST, "Erro de validação de campos", errors);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<Map<String, Object>> handleCartaoException(BusinessException ex){
        List<String> error = new ArrayList<>();
        error.add(ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro relacionado ao Cartão", error);
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioException(BusinessException ex){
        List<String> error = new ArrayList<>();
        error.add(ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro relacionado ao Usuário", error);
    }

    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity<Map<String, Object>> handleTransacoesException(BusinessException ex){
        List<String> error = new ArrayList<>();
        error.add(ex.getMessage());
        return  buildResponse(HttpStatus.BAD_REQUEST, "Erro relacionado a Transações.", error);
    }



    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, List<String> message){
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);

        return ResponseEntity.status(status).body(response);
    }
}
