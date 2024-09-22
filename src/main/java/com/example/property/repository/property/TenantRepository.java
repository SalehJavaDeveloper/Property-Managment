package com.example.property.repository.property;

import com.example.property.entity.property.TenantEntity;
import com.example.property.entity.property.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends  JpaRepository<TenantEntity,Long>, JpaSpecificationExecutor<TenantEntity> {
    TenantEntity getTenantEntityByName(String tenantName);
    List<TenantEntity> getTenantEntitiesByActivate(boolean act);

    List<TenantEntity> findByUnitEntity(UnitEntity unitEntity);
}
