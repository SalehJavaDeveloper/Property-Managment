package com.example.property.service.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.TenantRequestDto;
import com.example.property.dto.property_response.TenantResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TenantService {

    Page<TenantResponseDto> findAllTenant(int pageNumber, int pageSize);

    Page<TenantResponseDto> getSpecification(RequestDto requestDto);

    TenantResponseDto findTenantById(Long id) throws MethodArgumentNotValidException;

    void saveTenant(TenantRequestDto tenantRequestDto) throws MethodArgumentNotValidException;

    void deleteTenantById(Long id) throws MethodArgumentNotValidException;

    void activateTenant(List<Long> id) throws MethodArgumentNotValidException;
    void deactivateTenant(List<Long> id) throws MethodArgumentNotValidException;

    void updateTenant(TenantRequestDto tenantRequestDto) throws MethodArgumentNotValidException;

    int activeTenantNumber();
}
