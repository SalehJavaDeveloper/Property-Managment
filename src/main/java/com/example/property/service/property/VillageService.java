package com.example.property.service.property;

import com.example.property.dto.property_request.VillageRequestDto;
import com.example.property.dto.property_response.VillageResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VillageService {

    void saveVillage(VillageRequestDto villageDto) throws MethodArgumentNotValidException;

    Page<VillageResponseDto> findAllVillage(int pageNumber, int pageSize);

    VillageResponseDto findVillageById(Long id) throws MethodArgumentNotValidException;


    void deleteVillageById(Long id) throws MethodArgumentNotValidException;

    void updateVillage(VillageRequestDto villageDto) throws MethodArgumentNotValidException;
}
