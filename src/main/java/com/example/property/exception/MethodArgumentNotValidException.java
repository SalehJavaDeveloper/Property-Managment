package com.example.property.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Getter
@Setter
public class MethodArgumentNotValidException extends RuntimeException {

//    private final Set<String> errorMessages;
public MethodArgumentNotValidException(String message) {
    super(message);
}
//    public MethodArgumentNotValidException(Set<String> errorMessages) {
//        this.errorMessages = errorMessages;
//    }
//    public MethodArgumentNotValidException(String message) {
//        super(message);
//    }
}
