package com.example.property.repository.payment;


import com.example.property.entity.payment.PaymentBuildingEntity;
import com.example.property.entity.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentBuildingRepository extends JpaRepository<PaymentBuildingEntity, Long> {

    List<PaymentBuildingEntity> getPaymentBuildingEntitiesByPaymentEntity(PaymentEntity paymentEntity);

    void deleteByPaymentEntity(PaymentEntity updatedPaymentEntity);
}