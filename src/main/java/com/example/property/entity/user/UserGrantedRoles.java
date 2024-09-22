package com.example.property.entity.user;

import com.example.property.entity.companyRoles.UserSpecialPermissions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Table(name = "user_granted_roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserGrantedRoles {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_role")
    private String userRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userGrantedRoles",fetch = EAGER)
    @JsonIgnore
    private List<UserSpecialPermissions> userSpecialPermissions;
}
