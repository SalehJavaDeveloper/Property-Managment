package com.example.property.dto.property_response;

import com.example.property.entity.property.CityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponseDto {
    private Long id;

    private CityEntity cityEntity;

    private String districtName;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
