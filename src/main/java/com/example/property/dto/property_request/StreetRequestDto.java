package com.example.property.dto.property_request;

import jakarta.validation.constraints.DecimalMin;
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
public class StreetRequestDto {

    private Long id;

    @NotNull(message = "District id not be null!")
    @DecimalMin(value = "1",message = "District id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long districtId;
    @Size(max = 50)
    @NotNull(message = "Street name not be null!")
    private String streetName;
}
