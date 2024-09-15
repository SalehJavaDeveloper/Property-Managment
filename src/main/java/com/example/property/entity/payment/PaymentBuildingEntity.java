package com.example.property.entity.payment;

import com.example.property.entity.property.BuildingEntity;
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
@Table(name = "payment_building")
public class PaymentBuildingEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @JsonIgnore
    BuildingEntity buildingEntity;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    PaymentEntity paymentEntity;
}