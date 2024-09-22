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
public class CityRequestDto {
    private Long id;

    @NotNull(message = "Country id not be null!")
    @DecimalMin(value = "1",message = "Country id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long countryId;
    @Size(max = 30)
    @NotNull(message = "City name not be null!")
    private String cityName;
}
