package com.example.property.service.property_communication;

import com.example.property.dto.communication_request.CommunicationUnitRequestDto;
import com.example.property.dto.communication_response.CommunicationUnitResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommunicationUnitService {

    void saveCommunicationUnit(CommunicationUnitRequestDto communicationUnitRequestDto);

    Page<CommunicationUnitResponseDto> findAllCommunicationUnits(int pageNumber, int pageSize);

    CommunicationUnitResponseDto findCommunicationUnitById(Long communicationUnitId);

    void updateCommunicationUnit(CommunicationUnitRequestDto communicationUnitRequestDto);

    Page<CommunicationUnitResponseDto> getSpecification(RequestDto requestDto);
}
