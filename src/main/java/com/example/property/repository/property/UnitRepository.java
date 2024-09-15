package com.example.property.repository.property;

import com.example.property.entity.property.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long>, JpaSpecificationExecutor<UnitEntity> {

    UnitEntity getUnitEntityByUnitType(String unitType);
    UnitEntity getUnitEntityByUnitNumber(String unitNumber);
    UnitEntity getUnitEntityByFloor(String floor);
    UnitEntity getUnitEntityByCountRoom(String unitRoom);
    UnitEntity getUnitEntityByArea(String unitArea);
    UnitEntity getUnitEntityByNote(String unitNote);


}
