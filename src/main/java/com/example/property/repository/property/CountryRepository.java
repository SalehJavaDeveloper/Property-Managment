package com.example.property.repository.property;

import com.example.property.entity.property.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity,Long> {

    CountryEntity getCountryEntityByCountryName(String countryName);



}
