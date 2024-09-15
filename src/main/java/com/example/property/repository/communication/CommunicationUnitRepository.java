package com.example.property.repository.communication;

import com.example.property.entity.communication.CommunicationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationUnitRepository extends JpaRepository<CommunicationUnit,Long>, JpaSpecificationExecutor<CommunicationUnit> {
}
