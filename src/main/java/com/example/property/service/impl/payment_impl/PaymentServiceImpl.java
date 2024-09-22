package com.example.property.service.impl.payment_impl;

import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.payment.PaymentDetailedResponseDto;
import com.example.property.dto.payment.PaymentGeneralResponseDto;
import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.entity.payment.PaymentDetailedView;
import com.example.property.entity.payment.PaymentEntity2;
import com.example.property.entity.payment.PaymentGeneralView;
import com.example.property.entity.payment.PaymentProviderEntity;
import com.example.property.entity.payment.PaymentUnitEntity2;
import com.example.property.entity.payment.TransactionEntity2;
import com.example.property.enumuration.Response;
import com.example.property.enumuration.StatusType;
import com.example.property.exception.BaseException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.payment.PaymentMapper;
import com.example.property.mapper.payment.TransactionMapper;
import com.example.property.repository.payment.PaymentDetailedViewRepository;
import com.example.property.repository.payment.PaymentGeneralViewRepository;
import com.example.property.repository.payment.PaymentProviderRepository;
import com.example.property.repository.payment.PaymentRepository2;
import com.example.property.repository.payment.PaymentUnitRepository2;
import com.example.property.repository.payment.TransactionRepository2;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.payment.PaymentService;
import com.example.property.service.payment.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final TransactionService transactionService;

    private final UnitRepository unitRepository;
    private final PaymentRepository2 paymentRepository2;
    private final PaymentUnitRepository2 paymentUnitRepository2;
    private final TransactionRepository2 transactionRepository2;
    private final PaymentGeneralViewRepository paymentGeneralViewRepository;
    private final PaymentProviderRepository paymentProviderRepository;
    private final PaymentDetailedViewRepository paymentDetailedViewRepository;

    private final PaymentMapper paymentMapper;
    private final TransactionMapper transactionMapper;
    private final FilterSpecification<PaymentGeneralView> paymentFilterSpecification;



    @Override
    @Transactional
    public void savePayment(PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        // check if given units belongs to same property
        int unitPropertyCount = unitRepository.countUnitProperty(paymentRequestDto.getUnitId());
        if (unitPropertyCount != 1) {
            log.info("Create payment request declined: {}, since unit property mismatch", paymentRequestDto);
            throw new BaseException(Response.UNIT_PROPERTY_MISMATCH);
        }

        PaymentEntity2 payment = paymentMapper.fromDTO(paymentRequestDto);
        paymentRepository2.save(payment);

        // generate payment unit records
        List<PaymentUnitEntity2> paymentUnitList = unitRepository
                .findAllUnitsByPaymentId(paymentRequestDto.getUnitId())
                .stream()
                .map(unit -> PaymentUnitEntity2.builder().unitEntity(unit).paymentEntity(payment).build())
                .toList();

        paymentUnitRepository2.saveAll(paymentUnitList);

        // generate transaction per payment unit

        // todo remove this and get payment provider dynamically
        PaymentProviderEntity paymentProviderEntity = paymentProviderRepository.getReferenceById(1L);

        List<TransactionEntity2> transactionList = paymentUnitList.stream().map(paymentUnit -> {
                    UUID transactionId = UUID.randomUUID();

                    return TransactionEntity2.builder()
                            .id(transactionId)
                            .paymentProvider(paymentProviderEntity)
                            .paymentUnit(paymentUnit)
                            .statusType(StatusType.NOT_PAID)
                            .url(transactionService.getPaymentLink(transactionId.toString(), payment.getAmount()))
                            .build();
                })
                .toList();

        transactionRepository2.saveAll(transactionList);

        log.info("Create payment request: {}, processed successfully", paymentRequestDto);
    }

    @Override
    public PaymentGeneralResponseDto getPaymentList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<PaymentGeneralView> paymentPage = paymentGeneralViewRepository.findAll(pageable);

        // map entity to dto
        List<PaymentGeneralResponseDto.PaymentDto> paymentDtoList =
                paymentMapper.generalViewToGeneralPaymentDto(paymentPage.getContent());

        return PaymentGeneralResponseDto.builder()
                .paymentList(paymentDtoList)
                .total(paymentPage.getTotalElements())
                .build();
    }

    @Override
    public PaymentDetailedResponseDto getPaymentDetails(Long id) {
        List<PaymentDetailedView> paymentDetailedViewList = paymentDetailedViewRepository.findByPaymentId(id);

        // map view to dto
        List<PaymentDetailedResponseDto.PaymentDto> paymentDetailedDtoList =
                paymentMapper.detailedViewToDetailedPaymentDto(paymentDetailedViewList);

        return PaymentDetailedResponseDto.builder()
                .paymentList(paymentDetailedDtoList)
                .total(paymentDetailedDtoList.size())
                .build();
    }

    @Override
    public Page<PaymentGeneralResponseDto.PaymentDto> getSpecification(RequestDto requestDto) {
        Specification<PaymentGeneralView> paymentSpecification =
                paymentFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());

        // fetch requested payments
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        Page<PaymentGeneralView> page = paymentGeneralViewRepository.findAll(paymentSpecification, pageable);

        // map view to dto
        List<PaymentGeneralResponseDto.PaymentDto> list = paymentMapper.generalViewToGeneralPaymentDto(page.getContent());

        return new PageImpl<>(list, pageable, page.getTotalElements());
    }


    @Override
    @Transactional
    public void updatePayment(Long paymentId, PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {

    }

}
