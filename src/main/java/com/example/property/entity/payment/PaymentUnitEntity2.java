package com.example.property.entity.payment;

import com.example.property.entity.property.UnitEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_unit2")
public class PaymentUnitEntity2 {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_unit2_id_seq"
    )
    @SequenceGenerator(
            name = "payment_unit2_id_seq",
            sequenceName = "payment_unit2_id_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @JsonIgnore
    private UnitEntity unitEntity;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonIgnore
    private PaymentEntity2 paymentEntity;
}