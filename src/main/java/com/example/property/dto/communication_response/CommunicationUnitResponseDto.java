package com.example.property.dto.communication_response;

import com.example.property.entity.communication.Communication;
import com.example.property.entity.property.UnitEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationUnitResponseDto {

    private Long id;

    private UnitEntity unitEntity;

    private Communication communication;
    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
