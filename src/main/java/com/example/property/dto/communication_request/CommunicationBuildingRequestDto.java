package com.example.property.dto.communication_request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationBuildingRequestDto {

    @NotNull(message = "Building id not be null!")
//    @DecimalMin(value = "1",message = "Communication id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private List<Long> buildingId;
    @NotNull(message = "Communication id not be null!")
    @DecimalMin(value = "1",message = "Communication id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long communicationId;
}
