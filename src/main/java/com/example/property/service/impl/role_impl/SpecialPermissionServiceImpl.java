package com.example.property.service.impl.role_impl;

import com.example.property.dto.role_request.UserSpecialPermissionRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.entity.companyRoles.CompanyRoles;
import com.example.property.entity.companyRoles.Permission;
import com.example.property.entity.companyRoles.UserSpecialPermissions;
import com.example.property.entity.user.User;
import com.example.property.entity.user.UserGrantedRoles;
import com.example.property.enumuration.Permissions;
import com.example.property.exception.AuthenticationException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.role.SpecialPermissionMapper;
import com.example.property.repository.role.SpecialPermissionRepository;
import com.example.property.repository.role.UserGrantedRoleRepository;
import com.example.property.service.impl.user_impl.AuthenticationServiceImpl;
import com.example.property.service.role.SpecialPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialPermissionServiceImpl implements SpecialPermissionService {

    private final UserGrantedRoleRepository userGrantedRoleRepository;
    private final SpecialPermissionRepository specialPermissionRepository;
    private final SpecialPermissionMapper specialPermissionMapper;
    private final AuthenticationServiceImpl authenticationService;

    @Override
    public AuthenticationResponse saveSpecialPermission(List<UserSpecialPermissionRequest> userSpecialPermissionRequests) {
        for (UserSpecialPermissionRequest userSpecialPermissionRequest : userSpecialPermissionRequests) {
            if(userSpecialPermissionRequest == null){
                throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
            }
            Boolean bool = true;
            Boolean hasPermission = true;
          List<Permission> permissions = userGrantedRoleRepository.getReferenceById(userSpecialPermissionRequest.getPermission_id()).getUser().getCompanyOfUsers().getPacket().getPermissions();
          List<CompanyRoles> companyRoles =  userGrantedRoleRepository.getReferenceById(userSpecialPermissionRequest.getPermission_id()).getUser().getCompanyOfUsers().getCompanyRoles();
          for(Permission permission: permissions){
              if (permission.getPermissions().equals(userSpecialPermissionRequest.getPermissions())) {
                  bool = false;
              }
          }
            for (Permission permission: permissions) {
                if (permission.getPermissions().equals(userSpecialPermissionRequest.getPermissions())) {
                    for (CompanyRoles companyRoles1 : companyRoles){
                        if (companyRoles1.getPermissions().equals(userSpecialPermissionRequest.getPermissions())){
                            hasPermission = false;
                        }
                    }
                    if(hasPermission){
                        UserSpecialPermissions userSpecialPermissions = specialPermissionMapper.fromDTO(userSpecialPermissionRequest);
                        userSpecialPermissions.setActivePermission(true);
                        userSpecialPermissions.setUserGrantedRoles(userGrantedRoleRepository.getReferenceById(userSpecialPermissionRequest.getPermission_id()));
                        User user = userGrantedRoleRepository.getReferenceById(userSpecialPermissionRequest.getPermission_id()).getUser();
                        Set<SimpleGrantedAuthority> collectPermissions = permissions.stream()
                                .map(specialpermission -> new SimpleGrantedAuthority(userSpecialPermissions.getPermissions().toString()))
                                .collect(Collectors.toSet());
                        user.addSpecialPermissions(collectPermissions);
                        specialPermissionRepository.save(userSpecialPermissions);
                    }
                    else {
                        throw new MethodArgumentNotValidException("Some permission already have, that's why save process is Unsuccessfully!!");
                    }
                }
            }
            if(bool){
                throw new AuthenticationException("Some permissions is not included your company's roles but anothers save Successfully!");
            }
        }
        return authenticationService.getPermissionJwt(userGrantedRoleRepository.getReferenceById(userSpecialPermissionRequests.get(0).getPermission_id()).getUser());
    }

    @Override
    public void deactivatePermission(Long role_id, Permissions permissions) {
        if(role_id == null && permissions == null){
            throw new MethodArgumentNotValidException("Not provide correctly data!");
        }
        UserGrantedRoles userGrantedRoles = userGrantedRoleRepository.getReferenceById(role_id);
        UserSpecialPermissions userSpecialPermissionRequests = specialPermissionRepository.findByUserGrantedRolesAndPermissions(userGrantedRoles,permissions);
        userSpecialPermissionRequests.setActivePermission(false);
        }
    }

