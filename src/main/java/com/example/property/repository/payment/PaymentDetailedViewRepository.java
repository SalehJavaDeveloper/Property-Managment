package com.example.property.repository.payment;

import com.example.property.entity.payment.PaymentDetailedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentDetailedViewRepository extends JpaRepository<PaymentDetailedView, UUID> {

    @Query("select pdw from PaymentDetailedView pdw where pdw.paymentId = :paymentId")
    List<PaymentDetailedView> findByPaymentId(Long paymentId);
}
