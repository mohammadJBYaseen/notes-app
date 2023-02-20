package com.yaseen.notesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    //TODO add generic handlers for each excpetion type, and add common json object for errpr responses.
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handle(NoteNotFoundException ex){
        ResponseEntity<String> response = new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handle(BindException ex){
        ResponseEntity<String> response = new ResponseEntity<>(ex.getCause().getMessage(), HttpStatus.BAD_GATEWAY);
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handle(AccessDeniedException ex){
        ResponseEntity<String> response = new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return response;
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handle(Exception ex){
        String msg = "Something went wrong" ;
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
