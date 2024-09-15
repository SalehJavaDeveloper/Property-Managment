package com.example.property.dto.user;

import com.example.property.annotation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "The full name should not be null!")
    @NotEmpty(message = "The full name should not be empty!")
    private String fullName;
    @NotNull(message = "The username should not be null!")
    @NotEmpty(message = "The username should not be empty!")
    @Column(unique = true)
    private String username;

    @NotNull(message = "The email should not be null!")
    @NotEmpty(message = "The email should not be empty!")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull(message = "The password should not be null!")
    @NotEmpty(message = "The password should not be empty!")
    @ValidPassword
    @Size(min = 8,message = "The password must be at least 8 digits")
    private String password;

    private String phoneNumber1;

    private String phoneNumber2;

    @NotNull(message = "The Role should not be null!")
    @NotEmpty(message = "The Role should not be empty!")
    private String roleName;

    private Long company_id;
}
