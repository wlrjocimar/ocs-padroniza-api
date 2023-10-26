package com.cenop4011.padroniza.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;







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
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manipularExcecoes(MethodArgumentNotValidException ex) {
      
            // Manipular exceções de validação
            MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) ex;
            Map<String, String> erros = new HashMap<>();

            validationException.getBindingResult().getAllErrors().forEach(error -> {
                String campo = ((FieldError) error).getField();
                String mensagem = error.getDefaultMessage();
                erros.put(campo, mensagem);
            });

            StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), erros.toString());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
        
    }
    
   
    
    // exceção generica sem tratamento específico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manipularExpection(Exception ex,ServletRequest request) {
 	   StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro genérico , informe a mensagem ao responsável :" +  ex.toString());
 	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
 	   
    }
    

    
    
    
 // exceção generica sem tratamento específico
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> acessoNegado(Exception ex,ServletRequest request) {
 	   StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Verifique a autenticação na api");
 	   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
 	   
    }
    
    
    @ExceptionHandler(PersonalBadRequest.class)
    public ResponseEntity<StandardError> personalBadRequest(PersonalBadRequest e,ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
   
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        // Trate a exceção de validação e retorne uma resposta personalizada
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    
}