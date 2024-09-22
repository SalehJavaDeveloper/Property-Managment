package com.example.property.dto.property_request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantRequestDto {

    private Long id;

    @NotEmpty(message = "Unit section should not be empty!")
    private Long unitId;

    @NotEmpty(message = "The name should not be empty!")
    private String name;

    @NotEmpty(message = "The surName should not be empty!")
    private String surname;

    private String fatherName;


    @Size(max = 15)
    private String phoneNumber1;

    @Size(max = 15)
    private String phoneNumber2;

    @Size(max = 15)
    private String homeNumber;

    //Boolean1
    private Boolean wpPhoneNumber1;

    private Boolean callPhoneNumber1;

    private Boolean smsPhoneNumber1;

    //Boolean2
    private Boolean wpPhoneNumber2;

    private Boolean callPhoneNumber2;

    private Boolean smsPhoneNumber2;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull(message = "Pin not be null!")
    private String pin;
}