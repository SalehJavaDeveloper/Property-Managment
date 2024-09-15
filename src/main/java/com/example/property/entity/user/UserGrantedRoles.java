package com.example.property.entity.user;

import com.example.property.dto.role_request.UserGrantedRequestDto;
import com.example.property.dto.role_response.UserGrantedResponseDto;
import com.example.property.entity.property.CityEntity;
import com.example.property.repository.role.UserGrantedRoleRepository;
import com.example.property.repository.user.UserRepository;
import com.example.property.role_config.RoleSimpleGrantedAuthority;
import com.example.property.service.impl.role_impl.UserGrantedRolesServiceImpl;
import com.example.property.service.role.UserGrantedRoleService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Table(name = "user_granted_roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserGrantedRoles{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_role")
    private String userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
