package com.example.property.dto.property_request;

import com.example.property.enumuration.MediaFileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaFileRequestDto {
    @JsonIgnore
    private String filePath;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private MediaFileType mediaFileType;

}
