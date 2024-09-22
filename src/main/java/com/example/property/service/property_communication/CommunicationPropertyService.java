package com.example.property.service.property_communication;

import com.example.property.dto.communication_request.CommunicationPropertyRequestDto;
import com.example.property.dto.communication_response.CommunicationPropertyResponseDto;
import com.example.property.dto.filter.RequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommunicationPropertyService {

    void saveCommunicationProperty(CommunicationPropertyRequestDto communicationPropertyRequestDto);

    Page<CommunicationPropertyResponseDto> findAllCommunicationProperty(int pageNumber, int pageSize);

    CommunicationPropertyResponseDto findCommunicationPropertyById(Long communicationPropertyId);

    void updateCommunicationProperty(CommunicationPropertyRequestDto communicationPropertyRequestDto);

    Page<CommunicationPropertyResponseDto> getSpecification(RequestDto requestDto);

}
