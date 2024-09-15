package com.example.property.dto.communication_response;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.communication.Communication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationBuildingResponseDto {

    private Long id;

    private BuildingEntity buildingEntity;

    private Communication communication;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
