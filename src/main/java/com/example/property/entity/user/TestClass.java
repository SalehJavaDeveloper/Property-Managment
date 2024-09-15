package com.example.property.entity.user;

import com.example.property.entity.companyRoles.CompanyRoles;
import com.example.property.repository.role.UserGrantedRoleRepository;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Getter
@Setter
public class TestClass {


    private final UserGrantedRoleRepository userGrantedRoleRepository;

    public TestClass(UserGrantedRoleRepository userGrantedRoleRepository) {
        this.userGrantedRoleRepository = userGrantedRoleRepository;
    }


    public List<SimpleGrantedAuthority> setRolesForUser( ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          Long  userId = ((User)principal).getId();
        System.out.println(userId);
        List<CompanyRoles> companyRoles = userGrantedRoleRepository.findByUserId(userId).getUser().getCompanyOfUsers().getCompanyRoles();
        return companyRoles.stream()
                .map(companyRole -> new SimpleGrantedAuthority(companyRole.getPermissions().toString()))
                .collect(Collectors.toList());
    }
}
