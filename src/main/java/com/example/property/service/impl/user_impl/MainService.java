package com.example.property.service.impl.user_impl;

import com.example.property.entity.companyRoles.CompanyRoles;
import com.example.property.entity.companyRoles.UserSpecialPermissions;
import com.example.property.entity.user.User;
import com.example.property.entity.user.UserGrantedRoles;
import com.example.property.enumuration.Permissions;
import com.example.property.enumuration.UserPermissions;
import com.example.property.repository.role.CompanyRolesRepository;
import com.example.property.repository.role.UserGrantedRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("MainService")
@RequiredArgsConstructor
@Slf4j
public class MainService {
    private final CompanyRolesRepository companyRolesRepository;
    private final UserGrantedRoleRepository userGrantedRoleRepository;

    @PostConstruct
public void initializePermissions(){
       List<UserGrantedRoles> userGrantedRoles =userGrantedRoleRepository.findAll();
    for (UserGrantedRoles userGrantedRoles1 : userGrantedRoles) {
        User user = userGrantedRoles1.getUser();
        if(userGrantedRoles1.getUserRole().equals("USER")){
            Set<SimpleGrantedAuthority> authorityList = new HashSet<>();
            for (UserPermissions role : UserPermissions.values()) {
                authorityList.add(new SimpleGrantedAuthority(role.name()));
            }
            System.out.println(user.getUsername());
            user.addPermissions(authorityList);
            System.out.println("ghiiiiii" + authorityList);
            user.getAuthorities();
        }else{
            List<CompanyRoles> companyRolesList = companyRolesRepository.findCompanyRolesByCompanyRoleName(userGrantedRoles1.getUserRole());
            Set<SimpleGrantedAuthority> collectPermissions = companyRolesList.stream()
                    .map(companyRole -> new SimpleGrantedAuthority(companyRole.getPermissions().toString()))
                    .collect(Collectors.toSet());
            if(!userGrantedRoles1.getUserSpecialPermissions().isEmpty()){
                List<UserSpecialPermissions> userSpecialPermissions = userGrantedRoles1.getUserSpecialPermissions();
                for (UserSpecialPermissions userSpecialPermissions1 : userSpecialPermissions) {
                    System.out.println(user.getUsername());
                    if(userSpecialPermissions1.getActivePermission()){
                        System.out.println(user.getUsername());
                        Permissions permissions = userSpecialPermissions1.getPermissions();
                        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permissions.toString());
                        System.out.println("sagdkajdg" + simpleGrantedAuthority);
                        collectPermissions.add(simpleGrantedAuthority);
                    }
                }
            }
            user.addPermissions(collectPermissions);
            user.getAuthorities();
        }
    }
}
}
