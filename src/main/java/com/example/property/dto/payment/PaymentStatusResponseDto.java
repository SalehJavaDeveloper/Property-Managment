package com.example.property.dto.payment;

import com.example.property.enumuration.PaymentProviderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusResponseDto {
    private PaymentProviderStatus status;
}
