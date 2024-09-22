package com.example.property.entity.communication;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
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
@Table(name = "communication_building")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommunicationBuilding extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "building_id")
//    @JsonIgnore
//    private BuildingEntity buildingEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "communication_building_building",
            joinColumns = @JoinColumn(name = "com_building_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private List<BuildingEntity> buildingEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communication_id")
    @JsonIgnore
    private Communication communication;
}
