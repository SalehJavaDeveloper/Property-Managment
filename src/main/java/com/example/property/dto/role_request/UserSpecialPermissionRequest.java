package com.example.property.dto.role_request;

import com.example.property.enumuration.Permissions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserSpecialPermissionRequest {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Permissions permissions;

    private Long permission_id;
}
