package com.example.property.dto.payment;

import com.example.property.enumuration.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentGeneralResponseDto {
    private List<PaymentDto> paymentList;
    private long total;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PaymentDto {
        private Long paymentId;
        private String propertyName;
        private String serviceType;
        private PaymentType paymentType;
        private int unitCount;
        private BigDecimal amount;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createDate;
        private LocalDate dueDate;
    }
}