package com.example.property.service.impl.communication_impl;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.entity.communication.Communication;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.communication.CommunicationMapper;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.service.property_communication.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationMapper communicationMapper;

    private final CommunicationRepository communicationRepository;

    private final FilterSpecification<Communication> communicationFilterSpecification;
    @Override
    public void saveCommunication(CommunicationDto communicationDto) {
        if(communicationDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        Communication communication = communicationMapper.fromDTO(communicationDto);
        communication.setDeliveryDate(LocalDate.now());
        communicationRepository.save(communication);
    }

    @Override
    public Page<CommunicationDto> findAllCommunication(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return communicationMapper.mapPageEntityToPageResponse(communicationRepository.findAll(pageAble));
    }

    @Override
    public CommunicationDto findCommunicationById(Long communicationId) {
        if(!communicationRepository.findById(communicationId).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified communication!");
        }
        return communicationMapper.toDTO(communicationRepository.findById(communicationId).get());
    }

    @Override
    public void updateCommunication(CommunicationDto communicationDto) {
        if(communicationDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        Communication communication = communicationMapper.fromDTO(communicationDto);
        communication.setDeliveryDate(LocalDate.now());
        communicationRepository.save(communication);
    }

    @Override
    public Page<CommunicationDto> getSpecification(RequestDto requestDto) {
        Specification<Communication> communicationSpecification = communicationFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return communicationMapper.mapPageEntityToPageResponse(communicationRepository.findAll(communicationSpecification, pageable));
    }
}
