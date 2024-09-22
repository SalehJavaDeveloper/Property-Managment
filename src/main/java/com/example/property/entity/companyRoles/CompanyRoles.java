package com.example.property.entity.companyRoles;

import com.example.property.enumuration.Permissions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "company_roles")
public class CompanyRoles {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_role_name")
    private String companyRoleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissions")
    private Permissions permissions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company companyRoles;
}
