package com.example.property.dto.property_response;

import com.example.property.entity.property.DistrictEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StreetResponseDto {

    private Long id;


    private DistrictEntity districtEntity;

    private String streetName;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
