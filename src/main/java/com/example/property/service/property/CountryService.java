package com.example.property.service.property;


import com.example.property.dto.property_request.CountryRequestDto;
import com.example.property.dto.property_response.CountryResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {

    void saveCountry(CountryRequestDto countryDto) throws MethodArgumentNotValidException;

    Page<CountryResponseDto> findAllCountry(int pageNumber, int pageSize);

    CountryResponseDto findCountryById(Long id) throws MethodArgumentNotValidException;


    void deleteCountryById(Long id) throws MethodArgumentNotValidException;

    void updateCountry(CountryRequestDto countryDto) throws MethodArgumentNotValidException;
}
