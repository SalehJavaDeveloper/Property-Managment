package com.example.property.dto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailedResponseDto {
    private List<PaymentDto> paymentList;
    private long total;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PaymentDto {
        //        private UUID transactionId;
//        private Long paymentId;
        private String property;
        private String building;
        private String unit;
        private BigDecimal amount;
        private String description;
        private String statusType;
        private String paymentLink;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime paymentDate;
        private String[] tenants;
    }

}