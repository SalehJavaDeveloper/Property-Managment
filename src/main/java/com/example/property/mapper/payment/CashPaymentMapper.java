package com.example.property.mapper.payment;

import com.example.property.dto.payment.CashPaymentRequestDto;
import com.example.property.entity.payment.PaymentEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CashPaymentMapper {
    PaymentEntity fromDTO(CashPaymentRequestDto dto);
}
