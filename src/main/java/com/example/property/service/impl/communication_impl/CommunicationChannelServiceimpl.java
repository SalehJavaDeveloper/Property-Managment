package com.example.property.service.impl.communication_impl;

import com.example.property.dto.communication_request.CommunicationChannelRequestDto;
import com.example.property.dto.communication_response.CommunicationChannelResponseDto;
import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.entity.communication.CommunicationChannel;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.communication.CommunicationChannelMapper;
import com.example.property.repository.communication.CommunicationChannelRepository;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.service.property_communication.CommunicationChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationChannelServiceimpl implements CommunicationChannelService {

    private final CommunicationChannelMapper communicationChannelMapper;

    private final CommunicationChannelRepository communicationChannelRepository;

    private final CommunicationRepository communicationRepository;

    private final FilterSpecification<CommunicationChannel> communicationChannelFilterSpecification;
    @Override
    public void saveCommunicationChannel(CommunicationChannelRequestDto communicationChannelRequestDto) {
        if(communicationChannelRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CommunicationChannel communicationChannel = communicationChannelMapper.fromDTO(communicationChannelRequestDto);
        communicationChannel.setCommunication(communicationRepository.getReferenceById(communicationChannelRequestDto.getCommunicationId()));
        communicationChannelRepository.save(communicationChannel);
    }

    @Override
    public Page<CommunicationChannelResponseDto> findAllCommunicationChannel(int pageNumber, int pageSize) {

        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return communicationChannelMapper.mapPageEntityToPageResponse(communicationChannelRepository.findAll(pageAble));
    }

    @Override
    public CommunicationChannelResponseDto findCommunicationChannelById(Long channelId) {
        if(!communicationChannelRepository.findById(channelId).isPresent()){
            throw new MethodArgumentNotValidException("Data not found with specified communication building!");
        }
        return communicationChannelMapper.toDTO(communicationChannelRepository.findById(channelId).get());
    }

    @Override
    public void updateCommunicationChannel(CommunicationChannelRequestDto communicationChannelRequestDto) {
        if(communicationChannelRequestDto == null){
            throw new MethodArgumentNotValidException("Data Arguments is not valid!");
        }
        CommunicationChannel communicationChannel = communicationChannelMapper.fromDTO(communicationChannelRequestDto);
        communicationChannel.setCommunication(communicationRepository.getReferenceById(communicationChannelRequestDto.getCommunicationId()));
        communicationChannelRepository.save(communicationChannel);
    }

    @Override
    public Page<CommunicationChannelResponseDto> getSpecification(RequestDto requestDto) {
        Specification<CommunicationChannel> propertySpecification = communicationChannelFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return communicationChannelMapper.mapPageEntityToPageResponse(communicationChannelRepository.findAll(propertySpecification, pageable));
    }
}
