package com.example.property.dto.communication_response;

import com.example.property.entity.communication.Communication;
import com.example.property.enumuration.ChannelType;
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
public class CommunicationChannelResponseDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    private Communication communication;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
