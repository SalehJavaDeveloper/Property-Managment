package com.example.property.repository.property;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.BuildingResponsePerson;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.PropertyResponsePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BuildingResPersonRepository extends JpaRepository<BuildingResponsePerson,Long>, JpaSpecificationExecutor<BuildingResponsePerson> {
    BuildingResponsePerson getPropertyResponsePersonByBuildingEntity(BuildingEntity buildingEntity);
}
