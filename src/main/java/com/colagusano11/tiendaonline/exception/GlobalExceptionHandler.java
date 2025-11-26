package com.colagusano11.tiendaonline.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleNotFound(ResponseStatusException ex ){
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }
    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<String> handleNotStock(StockInsuficienteException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
