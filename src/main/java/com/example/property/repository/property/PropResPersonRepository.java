package com.example.property.repository.property;

import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.PropertyResponsePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PropResPersonRepository extends JpaRepository<PropertyResponsePerson,Long>, JpaSpecificationExecutor<PropertyResponsePerson> {

   List<PropertyResponsePerson> findByPropertyEntity(PropertyEntity propertyEntity);

   PropertyResponsePerson getPropertyResponsePersonByPropertyEntity(PropertyEntity propertyEntity);
}
