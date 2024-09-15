package com.example.property.repository.role;

import com.example.property.entity.companyRoles.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacketRepository extends JpaRepository<Packet, Long> {
}
