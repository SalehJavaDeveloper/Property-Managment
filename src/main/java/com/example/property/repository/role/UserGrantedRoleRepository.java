package com.example.property.repository.role;

import com.example.property.entity.user.UserGrantedRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGrantedRoleRepository extends JpaRepository<UserGrantedRoles, Long> {
    UserGrantedRoles findByUserId(Long id);
}
