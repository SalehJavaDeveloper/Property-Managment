package com.example.property.entity.payment;

import com.example.property.enumuration.PaymentType;
import com.example.property.enumuration.ServiceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_general_view")
public class PaymentGeneralView {
    @Id
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "property")
    private String propertyName;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "unit_count")
    private int unitCount;

    @Column(name = "amount")
    private BigDecimal amount;
}
