package com.example.property.repository.property;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, JpaSpecificationExecutor<BuildingEntity> {

    BuildingEntity getBuildingEntityByName(String buildingName);



}
