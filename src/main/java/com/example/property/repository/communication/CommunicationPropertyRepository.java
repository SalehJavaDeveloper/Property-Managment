package com.example.property.repository.communication;

import com.example.property.entity.communication.CommunicationProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationPropertyRepository extends JpaRepository<CommunicationProperty,Long>, JpaSpecificationExecutor<CommunicationProperty> {
}
