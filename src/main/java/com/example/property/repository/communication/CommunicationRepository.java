package com.example.property.repository.communication;

import com.example.property.entity.communication.Communication;
import com.example.property.enumuration.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication,Long>, JpaSpecificationExecutor<Communication> {
    @Query("SELECT MAX(e.id) FROM Communication e")
    String findMaxId();

    List<Communication> findAllByDeliveryDate(LocalDate localDate);

    List<Communication> getCommunicationsByMessageStatus(MessageStatus messageStatus);
}

