package com.example.property.dto.filter;

import com.example.property.enumuration.GlobalOperator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {

    private List<SearchRequestDto> searchRequestDto;
    private GlobalOperator globalOperator;
    private PageRequestDto pageRequestDto;
}
