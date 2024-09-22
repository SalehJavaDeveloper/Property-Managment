package com.example.property.repository.communication;

import com.example.property.entity.communication.CommunicationChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationChannelRepository extends JpaRepository<CommunicationChannel,Long>, JpaSpecificationExecutor<CommunicationChannel> {
}
