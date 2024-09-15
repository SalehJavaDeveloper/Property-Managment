package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.communication.CommunicationProperty;
import com.example.property.enumuration.PropertyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "property")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PropertyEntity extends AuditAble<Long>{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    PropertyType propertyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private CountryEntity countryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private CityEntity cityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private DistrictEntity districtEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "village_id")
    @JsonIgnore
    private VillageEntity villageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_id")
    @JsonIgnore
    private StreetEntity streetEntity;

    @OneToMany(mappedBy = "propertyEntity")
    @JsonIgnore
    private List<BuildingEntity> buildingEntities;

    @OneToMany(mappedBy = "propertyEntity")
    @JsonIgnore
    private List<PropertyResponsePerson> A;

    @OneToMany(mappedBy = "propertyEntity")
    @JsonIgnore
    private List<CommunicationProperty> communicationProperties;

    @OneToMany(mappedBy = "propertyEntity")
    @JsonIgnore
    private List<TaskEntity> taskEntities;

    private String addressNote;

    private int countBuilding;

    private int countUnit;

    private String bankAccount;

    private String email;

    private float area;

    private boolean activate;

}
