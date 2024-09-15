package com.example.property.dto.role_request;

import com.example.property.enumuration.Permissions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PermissionRequestDto {

    private Long id;

    @NotNull(message = "Packet id not be null!")
    @DecimalMin(value = "1",message = "Packet id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long packetId;

    @Enumerated(EnumType.STRING)
    private Permissions permissions;
}
