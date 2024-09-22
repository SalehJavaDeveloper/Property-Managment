package com.example.property.service.role;

import com.example.property.dto.role_request.UserSpecialPermissionRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.enumuration.Permissions;

import java.util.List;

public interface SpecialPermissionService {

    public AuthenticationResponse saveSpecialPermission(List<UserSpecialPermissionRequest> userSpecialPermissionRequest);

    public void deactivatePermission(Long role_id, Permissions permissions);
}
