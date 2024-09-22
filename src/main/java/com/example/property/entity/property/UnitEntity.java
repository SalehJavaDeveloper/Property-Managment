package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.communication.CommunicationUnit;
import com.example.property.entity.communication.CommunicationMain;
import com.example.property.enumuration.UnitContractType;
import com.example.property.enumuration.UnitType;
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
@Table(name = "unit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UnitEntity extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private BuildingEntity buildingEntity;

    @OneToMany(mappedBy = "unitEntity")
    @JsonIgnore
    List<TenantEntity> tenantEntities;

    @OneToMany(mappedBy = "unitEntity")
    @JsonIgnore
    List<CommunicationUnit> communicationUnits;

    @OneToMany(mappedBy = "unitEntity")
    @JsonIgnore
    private List<TaskEntity> taskEntities;

    @ManyToMany(mappedBy = "unitEntity")
    @JsonIgnore
    private List<CommunicationMain> communicationUnit;

    private int countRoom;

    private float area;

    private int floor;

    private String unitNumber;

    private String note;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Enumerated(EnumType.STRING)
    private UnitContractType contractType;
}
