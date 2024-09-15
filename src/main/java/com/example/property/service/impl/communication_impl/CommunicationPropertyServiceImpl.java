package com.example.property.service.impl.communication_impl;

import com.example.property.dto.communication_request.CommunicationPropertyRequestDto;
import com.example.property.dto.communication_response.CommunicationPropertyResponseDto;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.entity.communication.CommunicationProperty;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.communication.CommunicationPropertyMapper;
import com.example.property.repository.communication.CommunicationPropertyRepository;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.service.property_communication.CommunicationPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationPropertyServiceImpl implements CommunicationPropertyService {

    private final CommunicationPropertyMapper communicationPropertyMapper;
    private final CommunicationPropertyRepository communicationPropertyRepository;
    private final PropertyRepository propertyRepository;
    private final CommunicationRepository communicationRepository;

    private final FilterSpecification<CommunicationProperty> communicationPropertyFilterSpecification;

    @Override
    public void saveCommunicationProperty(CommunicationPropertyRequestDto communicationPropertyRequestDto) {
        CommunicationProperty communicationProperty = communicationPropertyMapper.fromDTO(communicationPropertyRequestDto);
        communicationProperty.setPropertyEntity(propertyRepository.getReferenceById(communicationPropertyRequestDto.getPropertyId()));
        communicationPropertyRepository.save(communicationProperty);
    }

    @Override
    public Page<CommunicationPropertyResponseDto> findAllCommunicationProperty(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return communicationPropertyMapper.mapPageEntityToPageResponse(communicationPropertyRepository.findAll(pageAble));
    }

    @Override
    public CommunicationPropertyResponseDto findCommunicationPropertyById(Long communicationPropertyId) {
        if(!communicationPropertyRepository.findById(communicationPropertyId).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified communication building!");
        }
        return communicationPropertyMapper.toDTO(communicationPropertyRepository.findById(communicationPropertyId).get());
    }

    @Override
    public void updateCommunicationProperty(CommunicationPropertyRequestDto communicationPropertyRequestDto) {
        CommunicationProperty communicationProperty = communicationPropertyMapper.fromDTO(communicationPropertyRequestDto);
        communicationProperty.setPropertyEntity(propertyRepository.getReferenceById(communicationPropertyRequestDto.getPropertyId()));
        communicationPropertyRepository.save(communicationProperty);
    }

    @Override
    public Page<CommunicationPropertyResponseDto> getSpecification(RequestDto requestDto) {
        Specification<CommunicationProperty> communicationPropertySpecification = communicationPropertyFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return communicationPropertyMapper.mapPageEntityToPageResponse(communicationPropertyRepository.findAll(communicationPropertySpecification, pageable));
    }
}
