package com.example.property.service.impl.property_impl;


import com.example.property.dto.property_request.CountryRequestDto;
import com.example.property.dto.property_response.CountryResponseDto;
import com.example.property.entity.property.CountryEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.property.CountryMapper;
import com.example.property.repository.property.CountryRepository;
import com.example.property.service.property.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public void saveCountry(CountryRequestDto countryDto) throws MethodArgumentNotValidException {
        if (countryDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
         countryRepository.save(countryMapper.fromDTO(countryDto));
    }

    @Override
    public Page<CountryResponseDto> findAllCountry(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return countryMapper.mapPageEntityToPageResponse(countryRepository.findAll(pageAble));
    }

    @Override
    public CountryResponseDto findCountryById(Long id) throws MethodArgumentNotValidException {
        if(!countryRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Country!");
        }
        return countryMapper.tomainDTO(countryRepository.getReferenceById(id));
    }

    @Override
    public void deleteCountryById(Long id) throws MethodArgumentNotValidException {
        if(!countryRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Country!");
        }
        countryRepository.deleteById(id);
    }

    @Override
    public void updateCountry(CountryRequestDto countryDto) throws MethodArgumentNotValidException {
        if(countryDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CountryEntity countryEntity = countryMapper.fromDTO(countryDto);
        countryRepository.save(countryEntity);
    }
}
