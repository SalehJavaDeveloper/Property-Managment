package com.example.property.service.property;



import com.example.property.dto.property_request.StreetRequestDto;
import com.example.property.dto.property_response.StreetResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StreetService {

    void saveStreet(StreetRequestDto streetDto) throws MethodArgumentNotValidException;

    Page<StreetResponseDto> findAllStreet(int pageNumber, int pageSize);

    StreetResponseDto findStreetById(Long id) throws MethodArgumentNotValidException;

    void deleteStreetById(Long id) throws MethodArgumentNotValidException;

    void updateStreet(StreetRequestDto streetDto) throws MethodArgumentNotValidException;
}
