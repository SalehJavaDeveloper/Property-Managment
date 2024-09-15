package com.example.property.service.role;

import com.example.property.dto.role_request.PermissionRequestDto;
import com.example.property.dto.role_response.PermissionResponseDto;

import java.util.List;

public interface PermissionService {

    void savePermission(PermissionRequestDto permissionRequestDto);

    PermissionResponseDto findPermissionsById(Long id);

    List<PermissionResponseDto> findAllPermissions();


}
