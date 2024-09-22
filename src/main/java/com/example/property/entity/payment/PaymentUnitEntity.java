package com.example.property.entity.payment;

import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.property.UnitEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_unit")
public class PaymentUnitEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @JsonIgnore
    UnitEntity unitEntity;
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonIgnore
    PaymentEntity paymentEntity;
}