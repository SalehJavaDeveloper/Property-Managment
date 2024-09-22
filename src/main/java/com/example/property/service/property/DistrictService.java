package com.example.property.service.property;


import com.example.property.dto.property_request.DistrictRequestDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DistrictService {

    void saveDistrict(DistrictRequestDto districtDto) throws MethodArgumentNotValidException;

    Page<DistrictResponseDto> findAllDistrict(int pageNumber, int pageSize);

    DistrictResponseDto findDistrictById(Long id) throws MethodArgumentNotValidException;


    void deleteDistrictById(Long id) throws MethodArgumentNotValidException;

    void updateDistrict(DistrictRequestDto districtDto) throws MethodArgumentNotValidException;
}
