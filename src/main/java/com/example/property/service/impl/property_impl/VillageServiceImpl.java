package com.example.property.service.impl.property_impl;

import com.example.property.dto.property_request.VillageRequestDto;
import com.example.property.dto.property_response.VillageResponseDto;
import com.example.property.entity.property.VillageEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.property.VillageMapper;
import com.example.property.repository.property.DistrictRepository;
import com.example.property.repository.property.VillageRepository;
import com.example.property.service.property.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {

    private final VillageRepository villageRepository;

    private final DistrictRepository districtRepository;

    private final VillageMapper villageMapper;

    @Override
    public void saveVillage(VillageRequestDto villageDto) throws MethodArgumentNotValidException {
        if (villageDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        VillageEntity villageEntity = villageMapper.fromDTO(villageDto);
        villageEntity.setDistrictEntity(districtRepository.getReferenceById(villageDto.getDistrictId()));
        villageRepository.save(villageEntity);
    }

    @Override
    public Page<VillageResponseDto> findAllVillage(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return villageMapper.mapPageEntityToPageResponse(villageRepository.findAll(pageAble));
    }

    @Override
    public VillageResponseDto findVillageById(Long id) throws MethodArgumentNotValidException {
        if(!villageRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Village!");
        }
        return villageMapper.toDTO(villageRepository.getReferenceById(id));
    }

    @Override
    public void deleteVillageById(Long id) throws MethodArgumentNotValidException {
        if(!villageRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified Village!");
        }
         villageRepository.deleteById(id);
    }

    @Override
    public void updateVillage(VillageRequestDto villageDto) throws MethodArgumentNotValidException {
        if(villageDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        VillageEntity villageEntity = villageMapper.fromDTO(villageDto);
        villageEntity.setDistrictEntity(districtRepository.getReferenceById(villageDto.getDistrictId()));
        villageRepository.save(villageEntity);
    }
}
