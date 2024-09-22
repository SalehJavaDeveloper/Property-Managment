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
public interface UnitRepository extends JpaRepository<UnitEntity, Long>, JpaSpecificationExecutor<UnitEntity> {

    UnitEntity getUnitEntityByUnitType(String unitType);

    UnitEntity getUnitEntityByUnitNumber(String unitNumber);

    UnitEntity getUnitEntityByFloor(String floor);

    UnitEntity getUnitEntityByCountRoom(String unitRoom);

    UnitEntity getUnitEntityByArea(String unitArea);

    UnitEntity getUnitEntityByNote(String unitNote);

    @Query(value = "select count(distinct property.id) " +
            "from unit " +
            "left join building on building.id = unit.building_id " +
            "left join property on property.id = building.property_id " +
            "where unit.id in :idList", nativeQuery = true)
    int countUnitProperty(List<Long> idList);

    @Query("SELECT u FROM UnitEntity u WHERE u.id IN :idList")
    List<UnitEntity> findAllUnitsByPaymentId(List<Long> idList);

    @Query("SELECT b FROM UnitEntity b WHERE b.buildingEntity in :buildingEntities")
    List<UnitEntity> findByBuildingList(List<BuildingEntity> buildingEntities);


}
