package com.example.property.dto.payment;

import com.example.property.enumuration.Currency;
import com.example.property.enumuration.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInitializationRequestDto {
    private String id;
    private BigDecimal amount;
    private Currency currency;
    private Language language;
}
