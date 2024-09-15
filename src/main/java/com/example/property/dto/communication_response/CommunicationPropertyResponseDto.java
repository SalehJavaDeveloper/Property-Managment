package com.example.property.dto.communication_response;

import com.example.property.entity.communication.Communication;
import com.example.property.entity.property.PropertyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationPropertyResponseDto {

    private Long id;

    private PropertyEntity propertyEntity;

    private Communication communication;
    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
