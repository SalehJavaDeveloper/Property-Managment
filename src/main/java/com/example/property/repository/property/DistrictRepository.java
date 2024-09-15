package com.example.property.repository.property;

import com.example.property.entity.property.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    DistrictEntity getDistrictEntityByDistrictName(String districtName);
}
