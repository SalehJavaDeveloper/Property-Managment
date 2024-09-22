package com.example.property.repository.role;

import com.example.property.entity.companyRoles.UserSpecialPermissions;
import com.example.property.entity.user.UserGrantedRoles;
import com.example.property.enumuration.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialPermissionRepository extends JpaRepository<UserSpecialPermissions, Long> {

    UserSpecialPermissions findByUserGrantedRolesAndPermissions(UserGrantedRoles userGrantedRoles, Permissions permissions);
}
