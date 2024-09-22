package com.example.property.repository.communication;

import com.example.property.entity.communication.CommunicationMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationMainRepository extends JpaRepository<CommunicationMain, Long>, JpaSpecificationExecutor<CommunicationMain> {
}
