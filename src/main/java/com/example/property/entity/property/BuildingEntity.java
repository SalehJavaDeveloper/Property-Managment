package com.example.property.entity.property;


import com.example.property.abstraction.AuditAble;
import com.example.property.entity.communication.CommunicationBuilding;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "building")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BuildingEntity extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private int countUnit;

    private int countEntrance;

    private int countFloor;

    private double area;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private PropertyEntity propertyEntity;

    @OneToMany(mappedBy = "buildingEntity")
    @JsonIgnore
    private List<UnitEntity> unitEntities;

    @OneToMany(mappedBy = "buildingEntity")
    @JsonIgnore
    private List<BuildingResponsePerson> buildingResponsePeople;

    @OneToMany(mappedBy = "buildingEntity")
    @JsonIgnore
    private List<CommunicationBuilding> communicationBuildings;

    @OneToMany(mappedBy = "buildingEntity")
    @JsonIgnore
    private List<TaskEntity> taskEntities;

}
