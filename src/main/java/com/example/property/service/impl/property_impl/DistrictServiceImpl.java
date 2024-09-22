package com.example.property.service.impl.property_impl;

import com.example.property.dto.property_request.DistrictRequestDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.entity.property.DistrictEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.property.DistrictMapper;
import com.example.property.repository.property.CityRepository;
import com.example.property.repository.property.DistrictRepository;
import com.example.property.service.property.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final CityRepository cityRepository;

    private final DistrictMapper districtMapper;

    @Override
    public void saveDistrict(DistrictRequestDto districtRequestDto) throws MethodArgumentNotValidException {
        if (districtRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        DistrictEntity districtEntity = districtMapper.fromDTO(districtRequestDto);
        districtEntity.setCityEntity(cityRepository.getReferenceById(districtRequestDto.getCityId()));
        districtRepository.save(districtEntity);
    }

    @Override
    public Page<DistrictResponseDto> findAllDistrict(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);

        return districtMapper.mapPageEntityToPageResponse(districtRepository.findAll(pageAble));
    }

    @Override
    public DistrictResponseDto findDistrictById(Long id) throws MethodArgumentNotValidException {
        if(!districtRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified District!");
        }
        return districtMapper.toDTO(districtRepository.getReferenceById(id));
    }

    @Override
    public void deleteDistrictById(Long id) throws MethodArgumentNotValidException {
        if(!districtRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified District!");
        }districtRepository.deleteById(id);
    }

    @Override
    public void updateDistrict(DistrictRequestDto districtDto) throws MethodArgumentNotValidException {
        if(districtDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        DistrictEntity districtEntity = districtMapper.fromDTO(districtDto);
        districtEntity.setCityEntity(cityRepository.getReferenceById(districtDto.getCityId()));
        districtRepository.save(districtEntity);
    }
}
