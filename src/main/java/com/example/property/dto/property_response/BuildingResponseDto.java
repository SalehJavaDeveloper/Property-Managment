package com.example.property.dto.property_response;

import com.example.property.entity.property.BuildingResponsePerson;
import com.example.property.entity.property.PropertyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingResponseDto {

    private Long id;

    private String name;

    private String description;

    private int countUnit;

    private int countEntrance;

    private int countFloor;

    private double area;

    private String note;

    private PropertyEntity propertyEntity;
    private List<BuildingResponsePerson> buildingResponsePeople;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
    private Boolean activate;
}
