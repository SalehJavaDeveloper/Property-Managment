package com.example.property.dto.payment;

import com.example.property.enumuration.PaymentType;
import com.example.property.enumuration.ServiceType;
import com.example.property.enumuration.StatusType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
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
public class PaymentRequestDto {
    @Enumerated(EnumType.STRING)
    PaymentType paymentType;
    Date recurringDate;
    @Min(0)
    Double amount;
    Date dueDate;
    @Size(max=100)
    String description;
    @Size(max=255)
    String notes;
    @Enumerated(EnumType.STRING)
    StatusType statusType;
    private List<Long> buildingId;
    private List<Long> unitId;
    private List<Long> propertyId;
    private List<ServiceType> serviceType;
}
