package com.example.property.dto.property_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
public class ResponsiblePersonDto {
    private Long id;
    private Long respPersonId;
}
