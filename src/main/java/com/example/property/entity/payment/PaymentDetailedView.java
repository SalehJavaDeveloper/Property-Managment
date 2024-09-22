package com.example.property.entity.payment;

import com.example.property.enumuration.StatusType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_detailed_view")
public class PaymentDetailedView {
    @Id
    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "property")
    private String property;

    @Column(name = "building")
    private String building;

    @Column(name = "unit")
    private String unit;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String statusType;

    @Column(name = "payment_link")
    private String paymentLink;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Type(StringArrayType.class)
    @Column(columnDefinition = "text[]")
    private String[] tenants;
}
