package com.example.property.service.impl.communication_impl;

import com.example.property.dto.communication_request.CommunicationRequestMainDto;
import com.example.property.dto.communication_response.CommunicationResponseMainDto;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.entity.communication.CommunicationMain;
import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.enumuration.AllMessageStatus;
import com.example.property.enumuration.MessageBuildingAll;
import com.example.property.enumuration.MessageStatus;
import com.example.property.enumuration.MessageUnitAll;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.communication.CommunicationMainMapper;
import com.example.property.repository.communication.CommunicationMainRepository;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.property_communication.CommunicationMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationMainServiceImpl implements CommunicationMainService {

     private final CommunicationMainRepository communicationMainRepository;
     private final PropertyRepository propertyRepository;
     private final BuildingRepository buildingRepository;
     private final UnitRepository unitRepository;

     private final CommunicationMainMapper communicationMainMapper;

    private final FilterSpecification<CommunicationMain> communicationMainFilterSpecification;

    @Override
    public void saveCommunicationMain(CommunicationRequestMainDto communicationRequestMainDto) {
        if(communicationRequestMainDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CommunicationMain communicationMain = communicationMainMapper.fromDTO(communicationRequestMainDto);
        communicationMain.setProperty(propertyRepository.getReferenceById(communicationRequestMainDto.getPropertyId()));

        if(communicationRequestMainDto.getLocalDateTime() == null){
            communicationMain.setLocalDateTime(LocalDateTime.now());
        }

        if(communicationRequestMainDto.getMessageBuildingAll()== MessageBuildingAll.BUILDING_ALL && communicationRequestMainDto.getMessageUnitAll() == MessageUnitAll.UNIT_ALL){
            List<BuildingEntity> buildingEntities = buildingRepository.findAllByPropertyEntity(propertyRepository.getReferenceById(communicationRequestMainDto.getPropertyId()));
            List<UnitEntity> unitEntities= unitRepository.findByBuildingList(buildingEntities);
            communicationMain.setBuildingEntity(buildingEntities);
            communicationMain.setUnitEntity(unitEntities);
            communicationMainRepository.save(communicationMain);
        }
      else if(communicationRequestMainDto.getMessageBuildingAll()== MessageBuildingAll.BUILDING_ALL){
            List<BuildingEntity> buildingEntities = buildingRepository.findAllByPropertyEntity(propertyRepository.getReferenceById(communicationRequestMainDto.getPropertyId()));
            List<UnitEntity> unitEntities= unitRepository.findAllById(communicationRequestMainDto.getUnitId());
            communicationMain.setBuildingEntity(buildingEntities);
            communicationMain.setUnitEntity(unitEntities);
            communicationMainRepository.save(communicationMain);
        } else if (communicationRequestMainDto.getMessageUnitAll() == MessageUnitAll.UNIT_ALL) {
            List<UnitEntity> unitEntities = unitRepository.findByBuildingList(buildingRepository.findAllById(communicationRequestMainDto.getBuildingId()));
            communicationMain.setBuildingEntity(buildingRepository.findAllById(communicationRequestMainDto.getBuildingId()));
            communicationMain.setUnitEntity(unitEntities);
            communicationMainRepository.save(communicationMain);
        }else{
            communicationMain.setBuildingEntity(buildingRepository.findAllById(communicationRequestMainDto.getBuildingId()));
            communicationMain.setUnitEntity(unitRepository.findAllById(communicationRequestMainDto.getUnitId()));
            communicationMainRepository.save(communicationMain);
        }
    }

    @Override
    public void updateCommunication(CommunicationRequestMainDto communicationRequestMainDto) {
        if(communicationRequestMainDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CommunicationMain communicationMain = communicationMainMapper.fromDTO(communicationRequestMainDto);
        CommunicationMain communicationMainTime = communicationMainRepository.getReferenceById(communicationRequestMainDto.getId());
        if(LocalDateTime.now().isAfter(communicationMainTime.getLocalDateTime())){
            throw new MethodArgumentNotValidException("This message already send! You not change schedule time!");
        }
        if(communicationRequestMainDto.getLocalDateTime() == null && LocalDateTime.now().isBefore(communicationMainTime.getLocalDateTime())){
            communicationMain.setLocalDateTime(LocalDateTime.now());
        }

        communicationMain.setProperty(propertyRepository.getReferenceById(communicationRequestMainDto.getPropertyId()));

        if(communicationRequestMainDto.getMessageBuildingAll()== MessageBuildingAll.BUILDING_ALL && communicationRequestMainDto.getMessageUnitAll() == MessageUnitAll.UNIT_ALL){
            List<BuildingEntity> buildingEntities = buildingRepository.findAllByPropertyEntity(propertyRepository.getReferenceById(communicationRequestMainDto.getPropertyId()));
            List<UnitEntity> unitEntities= unitRepository.findByBuildingList(buildingEntities);
            communicationMain.setBuildingEntity(buildingEntities);
            communicationMain.setUnitEntity(unitEntities);
            communicationMainRepository.save(communicationMain);
        }
        else if(communicationRequestMainDto.getMessageBuildingAll()== MessageBuildingAll.BUILDING_ALL){
            List<BuildingEntity> buildingEntities = buildingRepository.findAllByPropertyEntity(propertyRepository.getReferenceById(communicationRequestMainDto.getPropertyId()));
            List<UnitEntity> unitEntities= unitRepository.findAllById(communicationRequestMainDto.getUnitId());
            communicationMain.setBuildingEntity(buildingEntities);
            communicationMain.setUnitEntity(unitEntities);
            communicationMainRepository.save(communicationMain);
        } else if (communicationRequestMainDto.getMessageUnitAll() == MessageUnitAll.UNIT_ALL) {
            List<UnitEntity> unitEntities = unitRepository.findByBuildingList(buildingRepository.findAllById(communicationRequestMainDto.getBuildingId()));
            communicationMain.setBuildingEntity(buildingRepository.findAllById(communicationRequestMainDto.getBuildingId()));
            communicationMain.setUnitEntity(unitEntities);
            communicationMainRepository.save(communicationMain);
        }else{
            communicationMain.setBuildingEntity(buildingRepository.findAllById(communicationRequestMainDto.getBuildingId()));
            communicationMain.setUnitEntity(unitRepository.findAllById(communicationRequestMainDto.getUnitId()));
            communicationMainRepository.save(communicationMain);
        }
    }

    @Override
    public void cancelCommunication(Long communicationId, AllMessageStatus messageStatus) {
        CommunicationMain communicationMain = communicationMainRepository.getReferenceById(communicationId);

        if(LocalDateTime.now().isAfter(communicationMain.getLocalDateTime())){
            throw new MethodArgumentNotValidException("This message already send! You not order to canceled!");
        }else {
            communicationMain.setCancel(true);
        }
        communicationMainRepository.save(communicationMain);
    }

    @Override
    public Page<CommunicationResponseMainDto> getSpecification(RequestDto requestDto) {
        Specification<CommunicationMain> communicationMainSpecification = communicationMainFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return communicationMainMapper.mapPageEntityToPageResponse(communicationMainRepository.findAll(communicationMainSpecification, pageable));
    }
}
