package com.example.property.repository.property;

import com.example.property.entity.property.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends  JpaRepository<TenantEntity,Long>, JpaSpecificationExecutor<TenantEntity> {
    TenantEntity getTenantEntityByName(String tenantName);
}
