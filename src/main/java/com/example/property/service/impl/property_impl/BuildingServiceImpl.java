package com.example.property.service.impl.property_impl;

import com.example.property.config.ActivateConfiguration;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.BuildingRequestDto;
import com.example.property.dto.property_response.BuildingResponseDto;
import com.example.property.dto.property_request.ResponsiblePersonDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.BuildingResponsePerson;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.property.BuildingMapper;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.BuildingResPersonRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.property.BuildingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final PropertyRepository propertyRepository;
    private final ActivateConfiguration<BuildingEntity> buildingEntityActivateConfiguration;
    private final ActivateConfiguration<BuildingResponsePerson> buildingResponsePersonActivateConfiguration;
    private final BuildingMapper buildingMapper;
    private final UserRepository userRepository;
    private final BuildingResPersonRepository buildingResPersonRepository;
    private final FilterSpecification<BuildingEntity> buildingEntityFilterSpecification;


    @Override
    public void saveBuilding(BuildingRequestDto buildingRequestDto) throws MethodArgumentNotValidException {
        if(buildingRequestDto == null){
             throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }

        for(int i = 0; i < buildingRequestDto.getRespPerson().length; i++){
            if(!userRepository.findById(buildingRequestDto.getRespPerson()[i].getRespPersonId()).isPresent()){
                throw new MethodArgumentNotValidException("Sql exception with unsatisfied responsible person!");
            }else if (buildingRequestDto.getRespPerson()[i].getId() != 0) {
                throw new MethodArgumentNotValidException(("Responsible Person data not found for given Building!"));
            }
        }
        buildingRequestDto.setId(null);
        BuildingEntity buildingEntity = buildingMapper.fromDTO(buildingRequestDto);
          buildingEntity.setPropertyEntity(propertyRepository.getReferenceById(buildingRequestDto.getPropertyId()));
         buildingRepository.save(buildingEntity);
        log.info("buildingentity is {}",buildingEntity);

         for(int i = 0; i < buildingRequestDto.getRespPerson().length; i++){
             ResponsiblePersonDto responsiblePersonDto = buildingRequestDto.getRespPerson()[i];
             if(responsiblePersonDto.getId() == 0){
                 BuildingResponsePerson buildingResponsePerson = BuildingResponsePerson.builder()
                         .buildingEntity(buildingEntity)
                         .user(userRepository.getReferenceById(responsiblePersonDto.getRespPersonId()))
                         .build();
                 buildingResPersonRepository.save(buildingResponsePerson);
             }
         }
    }

    @Override
    public Page<BuildingResponseDto> findAllBuildings(int pageNumber, int pageSize, RequestDto requestDto) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);

        return buildingMapper.mapPageEntityToPageResponse(buildingRepository.findAll(pageAble));
    }

    @Override
    public Page<BuildingResponseDto> getSpecification(RequestDto requestDto) {
        Specification<BuildingEntity> buildingEntitySpecification = buildingEntityFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return buildingMapper.mapPageEntityToPageResponse(buildingRepository.findAll(buildingEntitySpecification, pageable));
    }


    @Override
    public BuildingResponseDto findBuildingById(Long id) throws MethodArgumentNotValidException {
         if(!buildingRepository.findById(id).isPresent()){
             throw new MethodArgumentNotValidException("Data not found with specified building!");
         }
        return buildingMapper.toDTO(buildingRepository.findById(id).get());
    }

    @Override
    public void deleteBuildingById(Long id) throws MethodArgumentNotValidException {
        if(!buildingRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified building!");
        }
      buildingRepository.deleteById(id);
    }

    @Override
    public void activateBuilding(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainID: id) {
            if(!buildingRepository.findById(mainID).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified building!");
            }
            buildingEntityActivateConfiguration.setActivateEntity(buildingRepository.getReferenceById(mainID));
        }
    }

    @Override
    public void deactivateBuilding(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainID: id) {
            if(!buildingRepository.findById(mainID).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified building!");
            }
            buildingEntityActivateConfiguration.setDeactivateEntity(buildingRepository.getReferenceById(mainID));
        }
    }

    @Override
    public void activateResponsiblePerson(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId: id) {
            if(!buildingResPersonRepository.findById(mainId).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified property!");
            }
            buildingResponsePersonActivateConfiguration.setActivateEntity(buildingResPersonRepository.getReferenceById(mainId));
        }
    }

    @Override
    public void deactivateResponsiblePerson(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId: id) {
            if(!buildingResPersonRepository.findById(mainId).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified property!");
            }
            buildingResponsePersonActivateConfiguration.setDeactivateEntity(buildingResPersonRepository.getReferenceById(mainId));
        }
    }

    @Override
    public void updateBuilding(BuildingRequestDto buildingRequestDto) throws MethodArgumentNotValidException {
        if(buildingRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        for(int i = 0; i < buildingRequestDto.getRespPerson().length; i++){
            if(!userRepository.findById(buildingRequestDto.getRespPerson()[i].getRespPersonId()).isPresent()){
                throw new MethodArgumentNotValidException("Sql exception with unsatisfied responsible person!");
            }
        }
        BuildingEntity buildingEntity = buildingMapper.fromDTO(buildingRequestDto);
        buildingEntity.setPropertyEntity(propertyRepository.getReferenceById(buildingRequestDto.getPropertyId()));
        buildingRepository.save(buildingEntity);
        for(int i = 0; i < buildingRequestDto.getRespPerson().length; i++){
            ResponsiblePersonDto responsiblePersonDto = buildingRequestDto.getRespPerson()[i];
            if(responsiblePersonDto.getId() == 0){
                BuildingResponsePerson buildingResponsePerson = BuildingResponsePerson.builder()
                        .buildingEntity(buildingEntity)
                        .user(userRepository.getReferenceById(responsiblePersonDto.getRespPersonId()))
                        .build();
                buildingResPersonRepository.save(buildingResponsePerson);
            }else {
                BuildingResponsePerson buildingResponsePerson = buildingResPersonRepository.getReferenceById(responsiblePersonDto.getId());
                buildingResponsePerson.setBuildingEntity(buildingEntity);
                buildingResponsePerson.setUser(userRepository.getReferenceById(responsiblePersonDto.getRespPersonId()));
                buildingResPersonRepository.save(buildingResponsePerson);
            }
        }
    }
}
