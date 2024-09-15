package com.example.property.dto.property_request;

import com.example.property.enumuration.PropertyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequestDto {

    private Long id;

    @Size(max = 30)
    @NotNull(message = "Property name not be null!")
    private String name;

    @Enumerated(EnumType.STRING)
    PropertyType propertyType;

    @NotNull(message = "Country id not be null!")
    @DecimalMin(value = "1",message = "Country id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long countryId;

    @NotNull(message = "City id not be null!")
    @DecimalMin(value = "1",message = "City id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long cityId;

    @NotNull(message = "District id not be null!")
    @DecimalMin(value = "1",message = "District id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long districtId;

    @NotNull(message = "Village id not be null!")
    @DecimalMin(value = "1",message = "Village id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long villageId;

    @NotNull(message = "Street id not be null!")
    @DecimalMin(value = "1",message = "Street id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long streetId;

    @Size(max = 50)
    @NotNull(message = "Property address note not be null!")
    private String addressNote;

    @NotNull(message = "Building count not be null!")
    @Min(value = 1,message = "Count building 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int countBuilding;

    @NotNull(message = "Unit count floor not be null!")
    @Min(value = 1,message = "Count unit 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int countUnit;

    @Size(max = 50)
    @NotNull(message = "Bank account not be null!")
    private String bankAccount;

    private ResponsiblePersonDto[] respPerson;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotNull(message = "Email not be null!")
    private String email;

    @NotNull(message = "Property area not be null!")
    @DecimalMin(value = "1",message = "Area 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private float area;
}
