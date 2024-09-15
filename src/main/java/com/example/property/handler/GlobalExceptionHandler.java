package com.example.property.handler;

import com.example.property.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDto> handleUnauthorizedException(UnauthorizedException e) {
        String[] message = {e.getMessage()};
        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.UNAUTHORIZED.value(),false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handleAuthenticationException(AuthenticationException e) {
        String[] message = {e.getMessage()};
        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.UNAUTHORIZED.value(),false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String[] message = {e.getMessage()};
        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.BAD_REQUEST.value(),false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        String[] message = {e.getMessage()};
        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.NOT_FOUND.value(),false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDto> handleResourceForbiddenException(ResourceNotFoundException e) {
        String[] message = {e.getMessage()};
        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.FORBIDDEN.value(),false);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        if(e.getMessage().contains("Bad credentials")){
            String[] messages = new String[]{"Username or password is not correct!"};
            ErrorResponse errorResponse = new ErrorResponse(messages, true);
            ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.UNAUTHORIZED.value(),false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
        }
        String[] message = {e.getMessage()};
        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse,HttpStatus.BAD_REQUEST.value(),false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
