package com.example.property.repository.payment;


import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.payment.PaymentEntity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository2 extends JpaRepository<PaymentEntity2, Long>, JpaSpecificationExecutor<PaymentEntity2> {

}