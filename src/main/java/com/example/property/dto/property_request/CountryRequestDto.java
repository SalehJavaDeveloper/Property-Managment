package com.example.property.dto.property_request;

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
public class CountryRequestDto {

    private Long id;

    @Size(max = 50)
    @NotNull(message = "Country name not be null!")
    private String countryName;

}
