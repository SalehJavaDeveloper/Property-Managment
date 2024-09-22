package com.example.property.repository.property;

import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, JpaSpecificationExecutor<BuildingEntity> {

    BuildingEntity getBuildingEntityByName(String buildingName);

    @Query("SELECT b FROM BuildingEntity b WHERE b.propertyEntity IN :propertyList")
    List<BuildingEntity> findByPropertyList(List<PropertyEntity> propertyList);
    List<BuildingEntity> findAllByPropertyEntity(PropertyEntity propertyEntity);
}
