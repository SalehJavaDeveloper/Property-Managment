package com.example.property.repository.payment;

import com.example.property.entity.payment.TransactionEntity;
import com.example.property.entity.payment.TransactionEntity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository2 extends JpaRepository<TransactionEntity2, UUID> {

}
