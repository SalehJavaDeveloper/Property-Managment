package com.example.property.dto.communication_request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationPropertyRequestDto {
//    @NotNull(message = "Property id not be null!")
//    @DecimalMin(value = "1",message = "Property id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private List<Long> propertyId;

    @NotNull(message = "Communication id not be null!")
    @DecimalMin(value = "1",message = "Communication id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long communicationId;

}
