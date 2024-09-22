package com.example.property.dto.user;

import com.example.property.annotation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AuthenticationRequest {

    @NotEmpty(message = "The username should not be empty!")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "The username should not be empty!")
    private String password;
}
