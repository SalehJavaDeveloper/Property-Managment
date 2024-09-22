package com.example.property.dto.payment;

import com.example.property.enumuration.PaymentType;
import com.example.property.enumuration.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentGenerationRequestDto {
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Min(0)
    private BigDecimal amount;

    private LocalDate recurringDate;

    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Size(max = 100)
    private String description;

    @Size(max = 255)
    private String notes;

    private ServiceType serviceType;

    private List<Long> unitList;

    private List<Long> buildingList;

    private List<Long> propertyList;

}
