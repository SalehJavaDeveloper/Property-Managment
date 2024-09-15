package com.example.property.role_config;

import com.example.property.entity.companyRoles.CompanyRoles;
import com.example.property.entity.user.User;
import com.example.property.repository.role.UserGrantedRoleRepository;
import com.example.property.repository.user.UserRepository;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleSimpleGrantedAuthority {
    private final UserGrantedRoleRepository userGrantedRoleRepository;
    private final UserRepository userRepository;





}
