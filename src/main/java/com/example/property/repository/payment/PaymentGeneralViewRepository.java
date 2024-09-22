package com.example.property.repository.payment;

import com.example.property.entity.payment.PaymentGeneralView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentGeneralViewRepository extends JpaRepository<PaymentGeneralView, Long>, JpaSpecificationExecutor<PaymentGeneralView> {

}
