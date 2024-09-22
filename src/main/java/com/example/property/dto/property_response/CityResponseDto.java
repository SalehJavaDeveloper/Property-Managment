package com.example.property.dto.property_response;


import com.example.property.entity.property.CountryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseDto {
    private Long id;

    private CountryEntity countryEntity;

    private String cityName;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
