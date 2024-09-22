package com.example.property.repository.payment;

import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.payment.PaymentEntity2;
import com.example.property.entity.payment.PaymentUnitEntity;
import com.example.property.entity.payment.PaymentUnitEntity2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentUnitRepository2 extends JpaRepository<PaymentUnitEntity2,Long> {
    List<PaymentUnitEntity2> getPaymentUnitEntitiesByPaymentEntity(PaymentEntity2 paymentEntity);

    void deleteByPaymentEntity(PaymentEntity2 updatedPaymentEntity);
}