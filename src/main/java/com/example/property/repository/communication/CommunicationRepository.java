package com.example.property.repository.communication;

import com.example.property.entity.communication.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication,Long>, JpaSpecificationExecutor<Communication> {
    @Query("SELECT MAX(e.id) FROM Communication e")
    String findMaxId();
}

