package com.example.property.service.impl.property_impl;


import com.example.property.dto.property_request.StreetRequestDto;
import com.example.property.dto.property_response.StreetResponseDto;
import com.example.property.entity.property.StreetEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.property.StreetMapper;
import com.example.property.repository.property.DistrictRepository;
import com.example.property.repository.property.StreetRepository;
import com.example.property.service.property.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    private final DistrictRepository districtRepository;

    private final StreetMapper streetMapper;

    @Override
    public void saveStreet(StreetRequestDto streetDto) throws MethodArgumentNotValidException {
        if (streetDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        StreetEntity streetEntity = streetMapper.fromDTO(streetDto);
        streetEntity.setDistrictEntity(districtRepository.getReferenceById(streetDto.getDistrictId()));
        streetRepository.save(streetEntity);
    }

    @Override
    public Page<StreetResponseDto> findAllStreet(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return streetMapper.mapPageEntityToPageResponse(streetRepository.findAll(pageable));
    }

    @Override
    public StreetResponseDto findStreetById(Long id) throws MethodArgumentNotValidException {
        if(!streetRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Street!");
        }
        return streetMapper.toDTO(streetRepository.getReferenceById(id));
    }

    @Override
    public void deleteStreetById(Long id) throws MethodArgumentNotValidException {
        if(!streetRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Street!");
        }
        streetRepository.deleteById(id);
    }

    @Override
    public void updateStreet(StreetRequestDto streetDto) throws MethodArgumentNotValidException {
        if(streetDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        StreetEntity streetEntity = streetMapper.fromDTO(streetDto);
        streetEntity.setDistrictEntity(districtRepository.getReferenceById(streetDto.getDistrictId()));
        streetRepository.save(streetEntity);
    }
}
