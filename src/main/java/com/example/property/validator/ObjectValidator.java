package com.example.property.validator;

import com.example.property.exception.MethodArgumentNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ObjectValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectValidate) throws MethodArgumentNotValidException {
        Set<ConstraintViolation<T>> violations = validator.validate(objectValidate);
        if(!violations.isEmpty()){
           var errorMessages =  violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
           String errors = errorMessages.toString();
            String result = errors.replaceAll("\\[|\\]", "");
           throw new MethodArgumentNotValidException(result);
        }
    }
}
