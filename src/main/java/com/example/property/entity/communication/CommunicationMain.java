package com.example.property.entity.communication;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.enumuration.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "communication_main")
public class CommunicationMain {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "communication_main_building_entity",
            joinColumns = @JoinColumn(name = "com_building_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private List<BuildingEntity> buildingEntity;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "communication_main_unit_entity",
            joinColumns = @JoinColumn(name = "com_unit_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    private List<UnitEntity> unitEntity;
    @Enumerated(EnumType.STRING)
    @Column(name = "channel_type")
    private ChannelType channelType;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;
    @Column(name = "subject")
    private String subject;
    @Column(name = "content")
    private String content;
    @Column(name = "note")
    private String note;
    @Column(name = "cancel")
    private boolean cancel;
    @Enumerated(EnumType.STRING)
    @Column(name = "message_status")
    private AllMessageStatus messageStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "message_building_all")
    private MessageBuildingAll messageBuildingAll;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_unit_all")
    private MessageUnitAll messageUnitAll;

}
