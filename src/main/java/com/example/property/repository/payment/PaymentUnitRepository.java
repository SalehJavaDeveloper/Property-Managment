package com.example.property.repository.payment;

import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.payment.PaymentUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentUnitRepository extends JpaRepository<PaymentUnitEntity,Long> {
    List<PaymentUnitEntity> getPaymentUnitEntitiesByPaymentEntity(PaymentEntity paymentEntity);

    void deleteByPaymentEntity(PaymentEntity updatedPaymentEntity);
}