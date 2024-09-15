package com.example.property.service.property_communication;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.filter.RequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface  CommunicationService {
    void saveCommunication(CommunicationDto communicationDto);

    Page<CommunicationDto> findAllCommunication(int pageNumber, int pageSize);

    CommunicationDto findCommunicationById(Long communicationId);

    void updateCommunication(CommunicationDto communicationDto);

    Page<CommunicationDto> getSpecification(RequestDto requestDto);
}
