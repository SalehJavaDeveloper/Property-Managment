package com.example.property.service.impl.property_impl;

import com.example.property.config.ActivateConfiguration;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.UnitRequestDto;
import com.example.property.dto.property_response.UnitResponseDto;
import com.example.property.entity.property.UnitEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.property.UnitMapper;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.property.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final BuildingRepository buildingRepository;
    private final UnitMapper unitMapper;
    private final ActivateConfiguration<UnitEntity> unitEntityActivateConfiguration;

    private final FilterSpecification<UnitEntity> unitEntityFilterSpecification;

    @Override
    public void saveUnit(UnitRequestDto unitRequestDto) throws MethodArgumentNotValidException {
        if (unitRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        UnitEntity unitEntity = unitMapper.fromDTO(unitRequestDto);
        unitEntity.setBuildingEntity(buildingRepository.getReferenceById(unitRequestDto.getBuildingId()));
        unitRepository.save(unitEntity);
    }

    @Override
    public Page<UnitResponseDto> findAllUnits(int pageNumber, int pageSize) {

        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return unitMapper.mapPageEntityToPageResponse(unitRepository.findAll(pageAble));
    }

    @Override
    public UnitResponseDto findUnitById(Long id) throws MethodArgumentNotValidException {
        if(!unitRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified unit!");
        }
         return unitMapper.toDTO(unitRepository.findById(id).get());
    }

    @Override
    public void deleteUnitById(Long id) throws MethodArgumentNotValidException {
        if(!unitRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified unit!");
        }
         unitRepository.deleteById(id);
    }

    @Override
    public void activateUnit(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId:id) {
            if(!unitRepository.findById(mainId).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified unit!");
            }
            unitEntityActivateConfiguration.setActivateEntity(unitRepository.getReferenceById(mainId));
        }
    }

    @Override
    public void deactivateUnit(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainId:id) {
            if(!unitRepository.findById(mainId).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified unit!");
            }
            unitEntityActivateConfiguration.setDeactivateEntity(unitRepository.getReferenceById(mainId));
        }
    }

    @Override
    public Page<UnitResponseDto> getSpecification(RequestDto requestDto) {
        Specification<UnitEntity> unitEntitySpecification = unitEntityFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return unitMapper.mapPageEntityToPageResponse(unitRepository.findAll(unitEntitySpecification, pageable));
    }

    @Override
    public void updateUnit(UnitRequestDto unitRequestDto) {
        UnitEntity unitEntity = unitMapper.fromDTO(unitRequestDto);
        unitEntity.setBuildingEntity(buildingRepository.getReferenceById(unitRequestDto.getBuildingId()));
        unitRepository.save(unitEntity);
    }
}
