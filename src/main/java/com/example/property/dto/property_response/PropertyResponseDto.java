package com.example.property.dto.property_response;

import com.example.property.entity.property.*;
import com.example.property.enumuration.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponseDto {
    private Long id;

    private String name;

    PropertyType propertyType;

    private CountryEntity countryEntity;

    private CityEntity cityEntity;

    private DistrictEntity districtEntity;

    private VillageEntity villageEntity;

    private StreetEntity streetEntity;

    private List<PropertyResponsePerson> propertyResponsePeople;

    private String addressNote;

    private int countBuilding;

    private int countUnit;

    private String bankAccount;

    private String email;

    private float area;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;

    private Boolean activate;
}
