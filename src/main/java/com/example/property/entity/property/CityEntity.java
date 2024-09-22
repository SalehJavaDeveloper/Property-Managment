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
@Table(name = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CityEntity extends AuditAble<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private CountryEntity countryEntity;

    @OneToMany(mappedBy = "cityEntity")
    @JsonIgnore
    List<DistrictEntity> districtEntities;

    @OneToMany(mappedBy = "cityEntity")
    @JsonIgnore
    List<PropertyEntity> propertyEntities;

    private String cityName;

}