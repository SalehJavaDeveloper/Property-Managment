package com.example.property.service.impl.payment_impl;

import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.dto.payment.PaymentResponseDto;
import com.example.property.entity.payment.*;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.exception.ResourceNotFoundException;
import com.example.property.mapper.payment.PaymentMapper;
import com.example.property.repository.payment.*;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PaymentServiceImpl")
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentBuildingRepository paymentBuildingRepository;
    private final PaymentUnitRepository paymentUnitRepository;
    private final PaymentServiceTypeRepository paymentServiceTypeRepository;
    private final PaymentPropertyRepository paymentPropertyRepository;
    private final PaymentMapper paymentMapper;
    private final BuildingRepository buildingRepository;
    private final UnitRepository unitRepository;
    private final PropertyRepository propertyRepository;


    @Override
    @Transactional
    public void savePayment(PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        if(paymentRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }else {
            PaymentEntity paymentEntity= paymentMapper.fromDTO(paymentRequestDto);
            System.out.println(paymentEntity);
            PaymentEntity paymentEntitysaved = paymentRepository.save(paymentEntity);

            paymentRequestDto.getBuildingId().stream()
                    .forEach(n->paymentBuildingRepository.save(PaymentBuildingEntity.builder()
                            .paymentEntity(paymentEntitysaved)
                            .buildingEntity(buildingRepository.getReferenceById(n))
                            .build()));

            paymentRequestDto.getUnitId().stream()
                    .forEach(n->paymentUnitRepository.save(PaymentUnitEntity.builder()
                            .paymentEntity(paymentEntitysaved)
                            .unitEntity(unitRepository.getReferenceById(n))
                            .build()));

            paymentRequestDto.getPropertyId().stream()
                    .forEach(n->paymentPropertyRepository.save(PaymentPropertyEntity.builder()
                            .paymentEntity(paymentEntitysaved)
                            .propertyEntity(propertyRepository.getReferenceById(n))
                            .build()));

            paymentRequestDto.getServiceType().stream()
                    .forEach(n->paymentServiceTypeRepository.save(PaymentServiceTypeEntity.builder()
                            .paymentEntities(paymentEntitysaved)
                            .serviceType(n)
                            .build()));

        }

    }

    @Override
    @Transactional
    public void updatePayment(Long paymentId, PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        if (paymentRequestDto == null) {
            throw new MethodArgumentNotValidException("Data Arguments are not valid!");
        }

        PaymentEntity paymentEntity = paymentMapper.fromDTO(paymentRequestDto);
        PaymentEntity existingPaymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + paymentId + " not found"));

        // Update the fields of the existing PaymentEntity with the new data
        existingPaymentEntity.setPaymentType(paymentEntity.getPaymentType());
        existingPaymentEntity.setRecurringDate(paymentEntity.getRecurringDate());
        existingPaymentEntity.setAmount(paymentEntity.getAmount());
        existingPaymentEntity.setDueDate(paymentEntity.getDueDate());
        existingPaymentEntity.setDescription(paymentEntity.getDescription());
        existingPaymentEntity.setNotes(paymentEntity.getNotes());
        existingPaymentEntity.setStatusType(paymentEntity.getStatusType());

        // Save the updated PaymentEntity
        PaymentEntity updatedPaymentEntity = paymentRepository.save(existingPaymentEntity);

        // Update related entities (PaymentBuildingEntity, PaymentUnitEntity, PaymentPropertyEntity, PaymentServiceTypeEntity)
        paymentBuildingRepository.deleteByPaymentEntity(updatedPaymentEntity);
        paymentRequestDto.getBuildingId().forEach(buildingId -> {
            PaymentBuildingEntity paymentBuildingEntity = PaymentBuildingEntity.builder()
                    .paymentEntity(updatedPaymentEntity)
                    .buildingEntity(buildingRepository.getReferenceById(buildingId))
                    .build();
            paymentBuildingRepository.save(paymentBuildingEntity);
        });

        paymentUnitRepository.deleteByPaymentEntity(updatedPaymentEntity);
        paymentRequestDto.getUnitId().forEach(unitId -> {
            PaymentUnitEntity paymentUnitEntity = PaymentUnitEntity.builder()
                    .paymentEntity(updatedPaymentEntity)
                    .unitEntity(unitRepository.getReferenceById(unitId))
                    .build();
            paymentUnitRepository.save(paymentUnitEntity);
        });

        paymentPropertyRepository.deleteByPaymentEntity(updatedPaymentEntity);
        paymentRequestDto.getPropertyId().forEach(propertyId -> {
            PaymentPropertyEntity paymentPropertyEntity = PaymentPropertyEntity.builder()
                    .paymentEntity(updatedPaymentEntity)
                    .propertyEntity(propertyRepository.getReferenceById(propertyId))
                    .build();
            paymentPropertyRepository.save(paymentPropertyEntity);
        });

        paymentServiceTypeRepository.deleteByPaymentEntities(updatedPaymentEntity);
        paymentRequestDto.getServiceType().forEach(serviceType -> {
            PaymentServiceTypeEntity paymentServiceTypeEntity = PaymentServiceTypeEntity.builder()
                    .paymentEntities(updatedPaymentEntity)
                    .serviceType(serviceType)
                    .build();
            paymentServiceTypeRepository.save(paymentServiceTypeEntity);
        });
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        paymentMapper.toDTOList(paymentRepository.findAll());
        return null;
    }

    @Override
    public PaymentResponseDto getPayment(Long paymentId) {
        PaymentEntity paymentEntity =   paymentRepository.getReferenceById(paymentId);
        return paymentMapper.toDTO(paymentEntity);
    }
}
