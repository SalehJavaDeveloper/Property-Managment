package com.example.property.dto.user;

import com.example.property.annotation.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {

    @NotNull(message = "The password should not be null!")
    @NotEmpty(message = "The password should not be empty!")
    @ValidPassword
    @Size(min = 8,message = "The password must be at least 8 digits")
    private String newPassword;

}
