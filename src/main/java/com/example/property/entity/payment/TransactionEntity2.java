package com.example.property.entity.payment;

import com.example.property.enumuration.StatusType;
import com.example.property.service.payment.PaymentProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction2")
public class TransactionEntity2 {

    @Id
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "payment_unit_id", referencedColumnName = "id")
    private PaymentUnitEntity2 paymentUnit;

    @ManyToOne
    @JoinColumn(name = "payment_provider_id", referencedColumnName = "id")
    private PaymentProviderEntity paymentProvider;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column(name = "url")
    private String url;

    @Column(name = "paymentDate")
    private LocalDateTime paymentDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
