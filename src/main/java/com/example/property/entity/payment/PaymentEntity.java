package com.example.property.entity.payment;

import com.example.property.enumuration.PaymentType;
import com.example.property.enumuration.ServiceType;
import com.example.property.enumuration.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    PaymentType paymentType;

    @Column(name = "recurring_date")
    Date recurringDate;

    @Column(name = "payment_date")
    Date paymentDate;

    @Column(name = "amount")
    Double amount;

    @Column(name = "due_date")
    Date dueDate;

    @Column(name = "description")
    String description;

    @Column(name = "notes")
    String notes;

    @Column(name = "order_id")
    String orderId;

    @Column(name = "transaction_id")
    String transactionId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusType statusType;


    @OneToMany(cascade = CascadeType.ALL)
    List<PaymentBuildingEntity> paymentBuildingEntities;
    @OneToMany(cascade = CascadeType.ALL)
    List<PaymentPropertyEntity> paymentProperties;
    @OneToMany(cascade = CascadeType.ALL)
    List<PaymentUnitEntity> paymentUnitEntities;

    @OneToMany(mappedBy = "paymentEntities")
    private List<PaymentServiceTypeEntity> paymentServiceTypeEntity;
}