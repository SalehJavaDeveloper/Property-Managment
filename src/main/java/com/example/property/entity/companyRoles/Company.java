package com.example.property.entity.companyRoles;


import com.example.property.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "company")
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @OneToOne(mappedBy = "company")
    private Packet packet;

    @OneToMany(mappedBy = "companyRoles")
    private List<CompanyRoles> companyRoles;

    @OneToMany(mappedBy = "companyOfUsers",fetch = FetchType.EAGER)
    private List<User> userList;
}
