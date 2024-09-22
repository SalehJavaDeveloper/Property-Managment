package com.example.property.dto.communication_response;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.communication.Communication;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationBuildingResponseDto {

    private Long id;

    private List<BuildingEntity> buildingEntities;

    private Communication communication;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
