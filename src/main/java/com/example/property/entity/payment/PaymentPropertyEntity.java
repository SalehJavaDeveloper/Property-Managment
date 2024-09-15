package com.example.property.entity.payment;

import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.property.PropertyEntity;
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
@Table(name = "payment_property")
public class PaymentPropertyEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @JsonIgnore
    PropertyEntity propertyEntity;
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonIgnore
    PaymentEntity paymentEntity;
}