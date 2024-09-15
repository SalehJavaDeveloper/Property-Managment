package com.example.property.dto.role_request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PacketRequestDto {

    private Long id;

    @NotNull(message = "Company id not be null!")
    @DecimalMin(value = "1",message = "Company id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long companyId;
}
