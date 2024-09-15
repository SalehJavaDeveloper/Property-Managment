package com.example.property.dto.communication_request;

import com.example.property.enumuration.ChannelType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationChannelRequestDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @NotNull(message = "Communication id not be null!")
    @DecimalMin(value = "1",message = "Communication id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long communicationId;
}
