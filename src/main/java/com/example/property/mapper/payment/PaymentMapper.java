package com.example.property.mapper.payment;

import com.example.property.dto.payment.PaymentDetailedResponseDto;
import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.dto.payment.PaymentGeneralResponseDto;
import com.example.property.entity.payment.PaymentDetailedView;
import com.example.property.entity.payment.PaymentEntity;
import com.example.property.entity.payment.PaymentEntity2;
import com.example.property.entity.payment.PaymentGeneralView;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {BigDecimal.class}
)
public interface PaymentMapper {
    PaymentGeneralResponseDto toDTO(PaymentEntity2 entity);
    List<PaymentGeneralResponseDto> toDTOList(List<PaymentEntity> entities);

    List<PaymentEntity> fromDTOList(List<PaymentRequestDto> dtoList);

    PaymentEntity2 fromDTO(PaymentRequestDto dto);

    default Page<PaymentGeneralResponseDto> mapPageEntityToPageResponse(Page<PaymentEntity2> payment) {
        List<PaymentGeneralResponseDto> dtoList = payment.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, payment.getPageable(), payment.getTotalElements());
    }


    // General view
    @Mapping(target = "amount", expression = "java(payment.getAmount().multiply(new BigDecimal(payment.getUnitCount())))")
    PaymentGeneralResponseDto.PaymentDto generalViewToGeneralPaymentDto(PaymentGeneralView payment);
    List<PaymentGeneralResponseDto.PaymentDto> generalViewToGeneralPaymentDto(List<PaymentGeneralView> payment);

    // Detailed view
    PaymentDetailedResponseDto.PaymentDto detailedViewToDetailedPaymentDto(PaymentDetailedView payment);
    List<PaymentDetailedResponseDto.PaymentDto> detailedViewToDetailedPaymentDto(List<PaymentDetailedView> payment);


}
