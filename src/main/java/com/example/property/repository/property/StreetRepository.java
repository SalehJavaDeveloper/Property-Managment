package com.example.property.repository.property;

import com.example.property.entity.property.StreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<StreetEntity, Long> {
    StreetEntity getStreetEntityByStreetName(String streetName);
}
