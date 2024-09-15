package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
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
@Table(name = "country")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CountryEntity extends AuditAble<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String countryName;

    @OneToMany(mappedBy = "countryEntity")
    @JsonIgnore
    private List<CityEntity> cityEntities;

    @OneToMany(mappedBy = "countryEntity")
    @JsonIgnore
    List<PropertyEntity> propertyEntities;

}
