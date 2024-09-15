package com.example.property.dto.payment;

import com.example.property.entity.payment.PaymentBuildingEntity;
import com.example.property.entity.payment.PaymentPropertyEntity;
import com.example.property.entity.payment.PaymentServiceTypeEntity;
import com.example.property.entity.payment.PaymentUnitEntity;
import com.example.property.enumuration.PaymentType;
import com.example.property.enumuration.StatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    PaymentType paymentType;
    Date recurringDate;
    Double amount;
    Date dueDate;
    String description;
    String notes;
    StatusType statusType;

    private List<PaymentBuildingEntity> paymentBuildingEntity;
    private List<PaymentUnitEntity> paymentUnitEntity;
    private List<PaymentPropertyEntity> paymentPropertyEntity;
    private List<PaymentServiceTypeEntity> paymentServiceTypeEntity;
}