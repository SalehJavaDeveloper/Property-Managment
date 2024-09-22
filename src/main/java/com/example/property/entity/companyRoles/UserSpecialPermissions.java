package com.example.property.entity.companyRoles;

import com.example.property.entity.user.User;
import com.example.property.entity.user.UserGrantedRoles;
import com.example.property.enumuration.Permissions;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "special_permission")
public class UserSpecialPermissions {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "special_permission")
    @Enumerated(EnumType.STRING)
    private Permissions permissions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id")
    private UserGrantedRoles userGrantedRoles;

    @Column(name = "active_Permission")
    private Boolean activePermission;
}
