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
@Table(name = "village")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VillageEntity extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private DistrictEntity districtEntity;

    @OneToMany(mappedBy = "villageEntity")
    @JsonIgnore
    List<PropertyEntity> propertyEntities;

    private String villageName;
    
}
