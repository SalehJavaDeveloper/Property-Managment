package com.example.property.dto.role_response;

import com.example.property.entity.companyRoles.Packet;
import com.example.property.enumuration.Permissions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PermissionResponseDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Permissions permissions;

    private Packet packet;
}
