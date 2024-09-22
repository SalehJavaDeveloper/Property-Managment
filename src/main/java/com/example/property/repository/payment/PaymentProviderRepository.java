package com.example.property.repository.payment;

import com.example.property.entity.payment.PaymentProviderEntity;
import com.example.property.service.payment.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProviderRepository extends JpaRepository<PaymentProviderEntity, Long> {
}
