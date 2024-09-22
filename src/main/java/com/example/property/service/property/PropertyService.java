package com.example.property.service.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.PropertyRequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PropertyService {


    void saveProperty(PropertyRequestDto propertyRequestDto) throws MethodArgumentNotValidException;

    Page<PropertyResponseDto> findAllProperty(int pageNumber, int pageSize);

    Page<PropertyResponseDto> getSpecification(RequestDto requestDto);

    PropertyResponseDto findPropertyById(Long id) throws MethodArgumentNotValidException;

    void deletePropertyById(Long id) throws MethodArgumentNotValidException;

    void activateProperty(List<Long> id) throws MethodArgumentNotValidException;
    void deactivateProperty(List<Long> id) throws MethodArgumentNotValidException;

     void activateResponsiblePerson(List<Long> id) throws  MethodArgumentNotValidException;

    void deactivateResponsiblePerson(List<Long> id) throws  MethodArgumentNotValidException;

    void updateProperty(PropertyRequestDto propertyRequestDto) throws MethodArgumentNotValidException;
}
