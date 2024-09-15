package com.example.property.service.impl.config_service_impl;

import com.example.property.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;

import java.util.Arrays;
@Slf4j
public class PasswordConstraintsValidator implements ConstraintValidator<ValidPassword, String>{


    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

    PasswordValidator passwordValidator = new PasswordValidator(
            Arrays.asList(
                    //Length rule. Min 8 max 128 characters
                    new LengthRule(8, 128),

                    //At least one upper case letter
                    new CharacterRule(EnglishCharacterData.UpperCase, 1),

                    //At least one lower case letter
                    new CharacterRule(EnglishCharacterData.LowerCase, 1),

                    //At least one number
                    new CharacterRule(EnglishCharacterData.Digit, 1),

                    //At least one special characters
                    new CharacterRule(EnglishCharacterData.Special, 1),

                    new WhitespaceRule()
            )
    );

        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(passwordValidator.getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
