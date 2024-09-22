package com.example.property.dto.property_response;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.enumuration.UnitContractType;
import com.example.property.enumuration.UnitType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitResponseDto {

    private Long id;

    private BuildingEntity buildingEntity;

    private int countRoom;

    private float area;

    private int floor;

    private String unitNumber;

    private String note;

    private UnitType unitType;

    private UnitContractType contractType;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;

    private Boolean activate;

}
