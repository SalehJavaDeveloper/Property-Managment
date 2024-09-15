package com.example.property.service.property;


import com.example.property.dto.property_request.CityRequestDto;
import com.example.property.dto.property_response.CityResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CityService {

     void saveCity(CityRequestDto cityDto) throws MethodArgumentNotValidException;

     Page<CityResponseDto> findAllCity(int pageNumber, int pageSize);

     CityResponseDto findCityById(Long id) throws MethodArgumentNotValidException;

     void deleteCityById(Long id) throws MethodArgumentNotValidException;

     void updateCity(CityRequestDto cityDto) throws MethodArgumentNotValidException;
}
