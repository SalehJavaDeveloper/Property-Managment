package com.example.property.service.impl.transaction_impl;

import com.example.property.dto.payment.PaymentInitializationRequestDto;
import com.example.property.dto.payment.PaymentInitializationResponseDto;
import com.example.property.dto.payment.PaymentStatusResponseDto;
import com.example.property.entity.payment.TransactionEntity2;
import com.example.property.enumuration.Currency;
import com.example.property.enumuration.Language;
import com.example.property.enumuration.PaymentProviderStatus;
import com.example.property.enumuration.StatusType;
import com.example.property.repository.payment.TransactionRepository2;
import com.example.property.service.payment.PaymentProvider;
import com.example.property.service.payment.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository2 transactionRepository2;
    private final PaymentProvider paymentProvider;

    @Override
    public String getPaymentLink(String transactionId, BigDecimal amount) {
        PaymentInitializationRequestDto paymentProviderRequest = PaymentInitializationRequestDto.builder()
                .id(transactionId)
                .amount(amount)
                .currency(Currency.AZN)
                .language(Language.AZ)
                .build();

        // todo handle errors, implement retry etc.
        try {
            PaymentInitializationResponseDto paymentProviderResponse = paymentProvider.initialize(paymentProviderRequest);
            return StringUtils.hasText(paymentProviderResponse.getUrl()) ? paymentProviderResponse.getUrl() : null;
        } catch (Exception e) {
            return null;
        }
    }

    // todo handle more complex cases
    @Override
    public void updatePaymentStatus(String transactionId) {
        log.info("Received callback with transaction id: {}", transactionId);

        Optional<TransactionEntity2> possibleTransaction = transactionRepository2.findById(UUID.fromString(transactionId));

        if (possibleTransaction.isPresent()) {
            PaymentStatusResponseDto response = paymentProvider.getStatus(transactionId);
            StatusType statusType = response.getStatus().equals(PaymentProviderStatus.APPROVED) ? StatusType.PAID : StatusType.FAILED;

            TransactionEntity2 transaction = possibleTransaction.get();
            transaction.setStatusType(statusType);
            transaction.setPaymentDate(LocalDateTime.now());
            transactionRepository2.save(transaction);
        }
    }
}
