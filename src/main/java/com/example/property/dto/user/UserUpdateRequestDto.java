package com.example.property.dto.user;

import com.example.property.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {
    @NotEmpty(message = "The full name should not be empty!")
    private String fullName;

    @NotEmpty(message = "The surname should not be empty!")
    private String username;

    @NotEmpty(message = "The email should not be empty!")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotEmpty(message = "The phone number should not be empty!")
    @Pattern(regexp="\\+\\d{12}", message="Telefon nömrəsi düzgün deyil!")
    private String phoneNumber1;

    @NotEmpty(message = "The phone number should not be empty!")
    @Pattern(regexp="\\+\\d{12}", message="Telefon nömrəsi düzgün deyil!")
    private String phoneNumber2;

    @NotEmpty(message = "The password should not be empty!")
    @ValidPassword
    @Size(min = 8,message = "The password must be at least 8 digits")
    private String password;
}
