package com.example.property.service.property_communication;

import com.example.property.dto.communication_request.CommunicationBuildingRequestDto;
import com.example.property.dto.communication_response.CommunicationBuildingResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommunicationBuildingService {

    void saveCommunicationBuilding(CommunicationBuildingRequestDto communicationBuildingRequestDto);

    Page<CommunicationBuildingResponseDto> findAllCommunicationBuilding(int pageNumber, int pageSize);

    CommunicationBuildingResponseDto findCommunicationBuildingById(Long communicationBuildingId);

    void updateCommunicationBuilding(CommunicationBuildingRequestDto communicationBuildingRequestDto);

    Page<CommunicationBuildingResponseDto> getSpecification(RequestDto requestDto);
}
