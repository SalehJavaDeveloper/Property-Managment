package com.example.property.repository.payment;


import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.payment.PaymentPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentPropertyRepository extends JpaRepository<PaymentPropertyEntity,Long> {

    List<PaymentPropertyEntity> getPaymentPropertyEntitiesByPaymentEntity(PaymentEntity paymentEntity);

    void deleteByPaymentEntity(PaymentEntity updatedPaymentEntity);
}