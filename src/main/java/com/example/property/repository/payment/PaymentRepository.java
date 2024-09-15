package com.example.property.repository.payment;


import com.example.property.entity.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("PaymentRepository")
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
   // List<PaymentEntity> findByPaymentDateBetween(Date startDate, Date endDate);
}