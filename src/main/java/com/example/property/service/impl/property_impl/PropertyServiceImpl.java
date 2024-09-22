package com.example.property.service.impl.property_impl;

import com.example.property.config.ActivateConfiguration;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.PropertyRequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.dto.property_request.ResponsiblePersonDto;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.PropertyResponsePerson;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.property.PropertyMapper;
import com.example.property.repository.property.*;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final CountryRepository countryRepository;
    private final FilterSpecification<PropertyEntity> propertyFilterSpecification;

    private final FilterSpecification<PropertyResponsePerson> propertyResponsePersonFilterSpecification;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final ActivateConfiguration<PropertyEntity> propertyEntityActivateConfiguration;
    private final ActivateConfiguration<PropertyResponsePerson> propertyResponsePersonActivateConfiguration;
    private final VillageRepository villageRepository;
    private final StreetRepository streetRepository;
    private final PropertyMapper propertyMapper;
    private final UserRepository userRepository;
    private final PropResPersonRepository propResPersonRepository;

    @Override
    public void saveProperty(PropertyRequestDto propertyRequestDto) throws MethodArgumentNotValidException {

        if(propertyRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        for(int i = 0; i < propertyRequestDto.getRespPerson().length; i++){
            if(!userRepository.findById(propertyRequestDto.getRespPerson()[i].getRespPersonId()).isPresent()){
                throw new MethodArgumentNotValidException("Sql exception with unsatisfied responsible person!");
            } else if (propertyRequestDto.getRespPerson()[i].getId() != 0) {
                throw new MethodArgumentNotValidException("Responsible Person data not found for given Property!");
            }
        }
        propertyRequestDto.setId(null);
        PropertyEntity propertyEntity = propertyMapper.fromDTO(propertyRequestDto);
        log.info("entity is {}",propertyEntity);
        propertyEntity.setCountryEntity(countryRepository.getReferenceById(propertyRequestDto.getCountryId()));
        propertyEntity.setCityEntity(cityRepository.getReferenceById(propertyRequestDto.getCityId()));
        propertyEntity.setDistrictEntity(districtRepository.getReferenceById(propertyRequestDto.getDistrictId()));
        propertyEntity.setVillageEntity(villageRepository.getReferenceById(propertyRequestDto.getVillageId()));
        propertyEntity.setStreetEntity(streetRepository.getReferenceById(propertyRequestDto.getStreetId()));
        propertyRepository.save(propertyEntity);

        for(int j = 0; j < propertyRequestDto.getRespPerson().length; j++){
            ResponsiblePersonDto responsiblePersonDto = propertyRequestDto.getRespPerson()[j];
            if(responsiblePersonDto.getId() == 0){
                PropertyResponsePerson propertyResponsePerson = PropertyResponsePerson.builder()
                        .propertyEntity(propertyEntity)
                        .user(userRepository.getReferenceById(responsiblePersonDto.getRespPersonId()))
                        .build();
                propResPersonRepository.save(propertyResponsePerson);
            }
        }
    }

    @Override
    public Page<PropertyResponseDto> findAllProperty(int pageNumber, int pageSize) {

        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return propertyMapper.mapPageEntityToPageResponse(propertyRepository.findAll(pageAble));
    }

    @Override
    public Page<PropertyResponseDto> getSpecification(RequestDto requestDto) {
        Specification<PropertyEntity> propertySpecification = propertyFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return propertyMapper.mapPageEntityToPageResponse(propertyRepository.findAll(propertySpecification, pageable));
    }



    @Override
    public PropertyResponseDto findPropertyById(Long id) throws MethodArgumentNotValidException {
        if(!propertyRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified property!");
        }
         return propertyMapper.toDTO(propertyRepository.findById(id).get());
    }

    @Override
    public void deletePropertyById(Long id) throws MethodArgumentNotValidException {
        if(!propertyRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified property!");
        }
         propertyRepository.deleteById(id);
    }

    @Override
    public void activateProperty(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId: id) {
            if (!propertyRepository.findById(mainId).isPresent()) {
                throw new MethodArgumentNotValidException("Data not found with specified property!");
            }
            propertyEntityActivateConfiguration.setActivateEntity(propertyRepository.getReferenceById(mainId));        }
    }

    @Override
    public void deactivateProperty(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId: id) {
            if (!propertyRepository.findById(mainId).isPresent()) {
                throw new MethodArgumentNotValidException("Data not found with specified property!");
            }
            log.info("entities isss {}",propertyRepository.getReferenceById(mainId));
            propertyEntityActivateConfiguration.setDeactivateEntity(propertyRepository.getReferenceById(mainId));
        }

    }

    @Override
    public void activateResponsiblePerson(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId: id)  {
            if(!propResPersonRepository.findById(mainId).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified property!");
            }
            propertyResponsePersonActivateConfiguration.setActivateEntity(propResPersonRepository.getReferenceById(mainId));
        }
    }

    @Override
    public void deactivateResponsiblePerson(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId: id)  {
            if(!propResPersonRepository.findById(mainId).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified property!");
            }
            propertyResponsePersonActivateConfiguration.setDeactivateEntity(propResPersonRepository.getReferenceById(mainId));
        }
    }

    @Override
    public void updateProperty(PropertyRequestDto propertyRequestDto) throws MethodArgumentNotValidException {
        if(propertyRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        for(int i = 0; i < propertyRequestDto.getRespPerson().length; i++){
            if(!userRepository.findById(propertyRequestDto.getRespPerson()[i].getRespPersonId()).isPresent()){
                throw new MethodArgumentNotValidException("Sql exception with unsatisfied responsible person!");
            }
        }
        PropertyEntity propertyEntity = propertyMapper.fromDTO(propertyRequestDto);
        propertyEntity.setCountryEntity(countryRepository.getReferenceById(propertyRequestDto.getCountryId()));
        propertyEntity.setCityEntity(cityRepository.getReferenceById(propertyRequestDto.getCityId()));
        propertyEntity.setDistrictEntity(districtRepository.getReferenceById(propertyRequestDto.getDistrictId()));
        propertyEntity.setVillageEntity(villageRepository.getReferenceById(propertyRequestDto.getVillageId()));
        propertyEntity.setStreetEntity(streetRepository.getReferenceById(propertyRequestDto.getStreetId()));
        propertyRepository.save(propertyEntity);

        for(int i = 0; i < propertyRequestDto.getRespPerson().length; i++){
            ResponsiblePersonDto responsiblePersonDto = propertyRequestDto.getRespPerson()[i];
            if(responsiblePersonDto.getId() == 0){
                PropertyResponsePerson propertyResponsePerson = PropertyResponsePerson.builder()
                        .propertyEntity(propertyEntity)
                        .user(userRepository.getReferenceById(responsiblePersonDto.getRespPersonId()))
                        .build();
                propResPersonRepository.save(propertyResponsePerson);
            } else {
                PropertyResponsePerson responsePerson = propResPersonRepository.getReferenceById(responsiblePersonDto.getId());
                responsePerson.setPropertyEntity(propertyEntity);
                responsePerson.setUser(userRepository.getReferenceById(responsiblePersonDto.getRespPersonId()));
                propResPersonRepository.save(responsePerson);
            }
        }

    }
}
