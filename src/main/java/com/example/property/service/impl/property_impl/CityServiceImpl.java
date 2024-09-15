package com.example.property.service.impl.property_impl;

import com.example.property.dto.property_request.CityRequestDto;
import com.example.property.dto.property_response.CityResponseDto;
import com.example.property.entity.property.CityEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.property.CityMapper;
import com.example.property.repository.property.CityRepository;
import com.example.property.repository.property.CountryRepository;
import com.example.property.service.property.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService
{
    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    public void saveCity(CityRequestDto cityRequestDto) throws MethodArgumentNotValidException {
        if (cityRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CityEntity cityEntity = cityMapper.fromDTO(cityRequestDto);
        cityEntity.setCountryEntity(countryRepository.getReferenceById(cityRequestDto.getCountryId()));
        cityRepository.save(cityRepository.save(cityEntity));
    }

    @Override
    public Page<CityResponseDto> findAllCity(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);

        return cityMapper.mapPageEntityToPageResponse(cityRepository.findAll(pageAble));
    }

    @Override
    public CityResponseDto findCityById(Long id) throws MethodArgumentNotValidException {
        if(!cityRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified City!");
        }
        return cityMapper.toDTO(cityRepository.getReferenceById(id));
    }

    @Override
    public void deleteCityById(Long id) throws MethodArgumentNotValidException {
        if(!cityRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified City!");
        }
         cityRepository.deleteById(id);
    }

    @Override
    public void updateCity(CityRequestDto cityDto) throws MethodArgumentNotValidException {
        if(cityDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CityEntity cityEntity = cityMapper.fromDTO(cityDto);
        cityEntity.setCountryEntity(countryRepository.getReferenceById(cityDto.getCountryId()));
        cityRepository.save(cityEntity);
    }
}
