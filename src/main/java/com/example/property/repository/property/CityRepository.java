package com.example.property.repository.property;

import com.example.property.entity.property.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity,Long> {
    CityEntity getCityEntityByCityName(String cityName);
}
