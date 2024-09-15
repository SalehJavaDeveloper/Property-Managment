package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
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
@Table(name = "district")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DistrictEntity extends AuditAble<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "districtEntity")
    @JsonIgnore
    List<PropertyEntity> propertyEntities;

    @OneToMany(mappedBy = "districtEntity")
    @JsonIgnore
    List<VillageEntity> villageEntities;

    @OneToMany(mappedBy = "districtEntity")
    @JsonIgnore
    List<StreetEntity> streetEntities;

    private String districtName;


}
