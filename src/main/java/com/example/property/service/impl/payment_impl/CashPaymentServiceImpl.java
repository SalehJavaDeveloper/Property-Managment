package com.example.property.service.impl.payment_impl;

import com.example.property.dto.payment.CashPaymentRequestDto;
import com.example.property.entity.payment.*;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.payment.CashPaymentMapper;
import com.example.property.mapper.payment.PaymentMapper;
import com.example.property.repository.payment.*;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.payment.CashPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentServiceImpl implements CashPaymentService
{
    private final CashPaymentMapper cashPaymentMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentBuildingRepository paymentBuildingRepository;
    private final BuildingRepository buildingRepository;
    private final PaymentUnitRepository paymentUnitRepository;
    private final UnitRepository unitRepository;
    private final PaymentPropertyRepository paymentPropertyRepository;
    private final PropertyRepository propertyRepository;
    private final PaymentServiceTypeRepository paymentServiceTypeRepository;
    @Override
    public void saveCashPayment(CashPaymentRequestDto cashPaymentRequestDto) {
        if(cashPaymentRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }else {
            PaymentEntity paymentEntity= cashPaymentMapper.fromDTO(cashPaymentRequestDto);
            System.out.println(paymentEntity);
            PaymentEntity paymentEntitysaved = paymentRepository.save(paymentEntity);

            cashPaymentRequestDto.getBuildingId().stream()
                    .forEach(n->paymentBuildingRepository.save(PaymentBuildingEntity.builder()
                            .paymentEntity(paymentEntitysaved)
                            .buildingEntity(buildingRepository.getReferenceById(n))
                            .build()));

            cashPaymentRequestDto.getUnitId().stream()
                    .forEach(n->paymentUnitRepository.save(PaymentUnitEntity.builder()
                            .paymentEntity(paymentEntitysaved)
                            .unitEntity(unitRepository.getReferenceById(n))
                            .build()));

            cashPaymentRequestDto.getPropertyId().stream()
                    .forEach(n->paymentPropertyRepository.save(PaymentPropertyEntity.builder()
                            .paymentEntity(paymentEntitysaved)
                            .propertyEntity(propertyRepository.getReferenceById(n))
                            .build()));

            cashPaymentRequestDto.getServiceType().stream()
                    .forEach(n->paymentServiceTypeRepository.save(PaymentServiceTypeEntity.builder()
                            .paymentEntities(paymentEntitysaved)
                            .serviceType(n)
                            .build()));

        }

    }
}
