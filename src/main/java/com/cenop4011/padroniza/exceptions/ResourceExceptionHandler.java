package com.cenop4011.padroniza.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;





@ControllerAdvice
public class ResourceExceptionHandler {
    
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objetoNaoEncontrado(ObjectNotFoundException e,ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
   
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
    
    @ExceptionHandler(ViolacaoIntegridadeException.class)
    public ResponseEntity<StandardError> integridadeViolada(ViolacaoIntegridadeException e,ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
   
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
    

    
    
    
    
    
    
    
    
    
}