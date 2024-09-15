package com.example.property.service.property_communication;

import com.example.property.dto.communication_request.CommunicationChannelRequestDto;
import com.example.property.dto.communication_response.CommunicationChannelResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommunicationChannelService {

    void saveCommunicationChannel(CommunicationChannelRequestDto communicationChannelRequestDto);

    Page<CommunicationChannelResponseDto> findAllCommunicationChannel(int pageNumber, int pageSize);

    CommunicationChannelResponseDto findCommunicationChannelById(Long channelId);

    void updateCommunicationChannel(CommunicationChannelRequestDto communicationChannelRequestDto);

    Page<CommunicationChannelResponseDto> getSpecification(RequestDto requestDto);
}
