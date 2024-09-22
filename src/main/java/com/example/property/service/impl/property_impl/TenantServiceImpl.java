package com.example.property.service.impl.property_impl;

import com.example.property.config.ActivateConfiguration;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.TenantRequestDto;
import com.example.property.dto.property_response.TenantResponseDto;
import com.example.property.dto.user.RegisterRequest;
import com.example.property.entity.property.TenantEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.property.TenantMapper;
import com.example.property.repository.property.TenantRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.service.property.TenantService;
import com.example.property.validator.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final UnitRepository unitRepository;

    private final ObjectValidator<TenantRequestDto> requestObjectValidator;
    private final FilterSpecification<TenantEntity> tenantFilterSpecification;

    private final ActivateConfiguration<TenantEntity> tenantEntityActivateConfiguration;


    @Override
    public Page<TenantResponseDto> findAllTenant(int pageNumber, int pageSize) {

        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return tenantMapper.mapPageEntityToPageResponse(tenantRepository.findAll(pageAble));
    }

    @Override
    public Page<TenantResponseDto> getSpecification(RequestDto requestDto) {
        Specification<TenantEntity> tenantSpecification = tenantFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return tenantMapper.mapPageEntityToPageResponse(tenantRepository.findAll(tenantSpecification, pageable));
    }

    @Override
    public TenantResponseDto findTenantById(Long id) throws MethodArgumentNotValidException {
        if(!tenantRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Tenant!");
        }
        return tenantMapper.toDTO(tenantRepository.getReferenceById(id));
    }

    @Override
    public void saveTenant(TenantRequestDto tenantRequestDto) throws MethodArgumentNotValidException {
//        requestObjectValidator.validate(tenantRequestDto);
        if(tenantRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        TenantEntity tenantEntity = tenantMapper.fromDTO(tenantRequestDto);
        tenantEntity.setUnitEntity(unitRepository.getReferenceById(tenantRequestDto.getUnitId()));
        tenantRepository.save(tenantEntity);
    }

    @Override
    public void deleteTenantById(Long id) throws MethodArgumentNotValidException {
        if(!tenantRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Tenant!");
        }
        tenantRepository.deleteById(id);
    }

    @Override
    public void activateTenant(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainID: id) {
            if(!tenantRepository.findById(mainID).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified Tenant!");
            }
            tenantEntityActivateConfiguration.setActivateEntity(tenantRepository.getReferenceById(mainID));
        }
    }

    @Override
    public void deactivateTenant(List<Long> id) throws MethodArgumentNotValidException {
        for (Long mainID: id) {
            if(!tenantRepository.findById(mainID).isPresent()){
                throw new MethodArgumentNotValidException("Data not found with specified Tenant!");
            }
            tenantEntityActivateConfiguration.setDeactivateEntity(tenantRepository.getReferenceById(mainID));
        }
    }

    @Override
    public void updateTenant(TenantRequestDto tenantRequestDto) throws MethodArgumentNotValidException {
        if(tenantRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        TenantEntity tenantEntity = tenantMapper.fromDTO(tenantRequestDto);
        tenantEntity.setUnitEntity(unitRepository.getReferenceById(tenantRequestDto.getUnitId()));
        tenantRepository.save(tenantEntity);
    }

    @Override
    public int activeTenantNumber() {
        List<TenantEntity> tenantEntitiesByActivate = tenantRepository.getTenantEntitiesByActivate(true);
        return tenantEntitiesByActivate.size();
    }
}
