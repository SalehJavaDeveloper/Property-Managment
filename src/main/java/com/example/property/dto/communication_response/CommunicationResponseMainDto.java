package com.example.property.dto.communication_response;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.enumuration.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CommunicationResponseMainDto {

    private Long id;
    private PropertyEntity propertyEntity;
    private List<BuildingEntity> buildingEntity;
    private List<UnitEntity> unitEntity;
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;
    private LocalDateTime localDateTime;
    private String subject;
    private String content;
    private String note;
    private boolean cancel;
    @Enumerated(EnumType.STRING)
    private AllMessageStatus messageStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_building_all")
    private MessageBuildingAll messageBuildingAll;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_unit_all")
    private MessageUnitAll messageUnitAll;
}
