package com.example.property.mapper.payment;

import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.dto.payment.PaymentResponseDto;
import com.example.property.entity.payment.PaymentEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface PaymentMapper {
    PaymentResponseDto toDTO(PaymentEntity entity);
    List<PaymentResponseDto> toDTOList(List<PaymentEntity> entities);

    List<PaymentEntity> fromDTOList(List<PaymentRequestDto> dtoList);

    PaymentEntity fromDTO(PaymentRequestDto dto);
}