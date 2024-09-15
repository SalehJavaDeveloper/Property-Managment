package com.example.property.repository.communication;

import com.example.property.entity.communication.CommunicationBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationBuildingRepository extends JpaRepository<CommunicationBuilding,Long>, JpaSpecificationExecutor<CommunicationBuilding> {
}
