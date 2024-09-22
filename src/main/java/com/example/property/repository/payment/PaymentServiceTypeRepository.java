package com.example.property.repository.payment;


import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.payment.PaymentServiceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentServiceTypeRepository  extends JpaRepository<PaymentServiceTypeEntity,Long> {
    void deleteByPaymentEntities(PaymentEntity updatedPaymentEntity);
}