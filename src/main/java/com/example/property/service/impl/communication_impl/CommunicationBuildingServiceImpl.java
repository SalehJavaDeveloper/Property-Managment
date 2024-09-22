package com.example.property.service.impl.communication_impl;

import com.example.property.dto.communication_request.CommunicationBuildingRequestDto;
import com.example.property.dto.communication_response.CommunicationBuildingResponseDto;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.entity.communication.CommunicationBuilding;
import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.communication.CommunicationBuildingMapper;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.communication.CommunicationBuildingRepository;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.service.property_communication.CommunicationBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationBuildingServiceImpl implements CommunicationBuildingService {


    private final CommunicationBuildingMapper communicationBuildingMapper;
    private final CommunicationBuildingRepository communicationBuildingRepository;
    private final CommunicationRepository communicationRepository;
    private final BuildingRepository buildingRepository;
    private final FilterSpecification<CommunicationBuilding> communicationBuildingFilterSpecification;

    @Override
    public void saveCommunicationBuilding(CommunicationBuildingRequestDto communicationBuildingRequestDto) {
        CommunicationBuilding communicationBuilding = communicationBuildingMapper.fromDTO(communicationBuildingRequestDto);

        List<Long> buildingId = communicationBuildingRequestDto.getBuildingId();
        List<BuildingEntity> buildingEntities = new ArrayList<>();
        for (Long building : buildingId){
            BuildingEntity referenceById = buildingRepository.getReferenceById(building);
            buildingEntities.add(referenceById);
        }
        communicationBuilding.setBuildingEntities(buildingEntities);
        communicationBuilding.setCommunication(communicationRepository.getReferenceById(communicationBuildingRequestDto.getCommunicationId()));
        communicationBuildingRepository.save(communicationBuilding);
    }

    @Override
    public Page<CommunicationBuildingResponseDto> findAllCommunicationBuilding(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return communicationBuildingMapper.mapPageEntityToPageResponse(communicationBuildingRepository.findAll(pageAble));
    }

    @Override
    public CommunicationBuildingResponseDto findCommunicationBuildingById(Long communicationBuildingId) {
        if(!communicationBuildingRepository.findById(communicationBuildingId).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified communication building!");
        }
        return communicationBuildingMapper.toDTO(communicationBuildingRepository.findById(communicationBuildingId).get());
    }

    @Override
    public void updateCommunicationBuilding(CommunicationBuildingRequestDto communicationBuildingRequestDto) {
        CommunicationBuilding communicationBuilding = communicationBuildingMapper.fromDTO(communicationBuildingRequestDto);
        List<Long> buildingId = communicationBuildingRequestDto.getBuildingId();
        List<BuildingEntity> buildingEntities = new ArrayList<>();
        for (Long building : buildingId){
            BuildingEntity referenceById = buildingRepository.getReferenceById(building);
            buildingEntities.add(referenceById);
        }
        communicationBuilding.setBuildingEntities(buildingEntities);
        communicationBuilding.setCommunication(communicationRepository.getReferenceById(communicationBuildingRequestDto.getCommunicationId()));
        communicationBuildingRepository.save(communicationBuilding);
    }

    @Override
    public Page<CommunicationBuildingResponseDto> getSpecification(RequestDto requestDto) {
        Specification<CommunicationBuilding> communicationBuildingSpecification = communicationBuildingFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return communicationBuildingMapper.mapPageEntityToPageResponse(communicationBuildingRepository.findAll(communicationBuildingSpecification, pageable));
    }
}
