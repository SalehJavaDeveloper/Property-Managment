package com.example.property.service.property_communication;

import com.example.property.dto.communication_request.CommunicationRequestMainDto;
import com.example.property.dto.communication_response.CommunicationResponseMainDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.enumuration.AllMessageStatus;
import com.example.property.enumuration.MessageStatus;
import org.springframework.data.domain.Page;

public interface CommunicationMainService {

    void saveCommunicationMain(CommunicationRequestMainDto communicationRequestMainDto);
    void updateCommunication(CommunicationRequestMainDto communicationRequestMainDto);

    void cancelCommunication(Long communicationId, AllMessageStatus messageStatus);

    Page<CommunicationResponseMainDto> getSpecification(RequestDto requestDto);
}
