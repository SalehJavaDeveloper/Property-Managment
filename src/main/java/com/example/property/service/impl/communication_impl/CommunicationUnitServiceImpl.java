package com.example.property.service.impl.communication_impl;

import com.example.property.dto.communication_request.CommunicationUnitRequestDto;
import com.example.property.dto.communication_response.CommunicationUnitResponseDto;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.entity.communication.CommunicationUnit;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.communication.CommunicationUnitMapper;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.repository.communication.CommunicationUnitRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.property_communication.CommunicationUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationUnitServiceImpl implements CommunicationUnitService {

    private final CommunicationUnitMapper communicationUnitMapper;
    private final CommunicationUnitRepository communicationUnitRepository;
    private final UnitRepository unitRepository;
    private final CommunicationRepository communicationRepository;
    private final FilterSpecification<CommunicationUnit> communicationUnitFilterSpecification;

    @Override
    public void saveCommunicationUnit(CommunicationUnitRequestDto communicationUnitRequestDto) {
        CommunicationUnit communicationUnit = communicationUnitMapper.fromDTO(communicationUnitRequestDto);
        communicationUnit.setUnitEntity(unitRepository.getReferenceById(communicationUnitRequestDto.getUnitId()));
        communicationUnit.setCommunication(communicationRepository.getReferenceById(communicationUnitRequestDto.getCommunicationId()));
        communicationUnitRepository.save(communicationUnit);
    }

    @Override
    public Page<CommunicationUnitResponseDto> findAllCommunicationUnits(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return communicationUnitMapper.mapPageEntityToPageResponse(communicationUnitRepository.findAll(pageAble));
    }

    @Override
    public CommunicationUnitResponseDto findCommunicationUnitById(Long communicationUnitId) {
        if(!communicationUnitRepository.findById(communicationUnitId).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified communication building!");
        }
        return communicationUnitMapper.toDTO(communicationUnitRepository.findById(communicationUnitId).get());
    }

    @Override
    public void updateCommunicationUnit(CommunicationUnitRequestDto communicationUnitRequestDto) {
        CommunicationUnit communicationUnit = communicationUnitMapper.fromDTO(communicationUnitRequestDto);
        communicationUnit.setUnitEntity(unitRepository.getReferenceById(communicationUnitRequestDto.getUnitId()));
        communicationUnit.setCommunication(communicationRepository.getReferenceById(communicationUnitRequestDto.getCommunicationId()));
        communicationUnitRepository.save(communicationUnit);
    }

    @Override
    public Page<CommunicationUnitResponseDto> getSpecification(RequestDto requestDto) {
        Specification<CommunicationUnit> communicationUnitSpecification = communicationUnitFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return communicationUnitMapper.mapPageEntityToPageResponse(communicationUnitRepository.findAll(communicationUnitSpecification, pageable));
    }
}
