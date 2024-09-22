package com.example.property.dto.property_response;

import com.example.property.entity.property.TaskEntity;
import com.example.property.enumuration.MediaFileType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MediaFileResponseDto {

    private UUID id;

    private String filePath;

    @Enumerated(EnumType.STRING)
    private MediaFileType mediaFileType;

    private TaskEntity taskEntity;
}
