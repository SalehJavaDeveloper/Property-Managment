package com.example.property.entity.user;


import com.example.property.abstraction.AuditAble;
import com.example.property.entity.communication.CommunicationUnit;
import com.example.property.entity.companyRoles.Company;
import com.example.property.entity.property.PropertyResponsePerson;
import com.example.property.entity.property.TaskEntity;
import com.example.property.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AuditAble<Long> implements UserDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String username;

    private String email;

    private String phoneNumber1;

    private String phoneNumber2;

    private String password;

    private String resetPasswordToken;

    @OneToMany(mappedBy = "user",fetch = EAGER)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PropertyResponsePerson> propertyResponsePeople;

    @OneToMany(mappedBy = "assignee")
    @JsonIgnore
    private Set<TaskEntity> taskEntities;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "company_id")
    private Company companyOfUsers;
    @OneToMany(mappedBy = "user",fetch = EAGER)
    @JsonIgnore
    private List<UserGrantedRoles> userGrantedRoles;

    @Transient
    @JsonIgnore
    private static Set<SimpleGrantedAuthority> permissions = new HashSet<>();

    public void addPermissions(Set<SimpleGrantedAuthority> permissions){
        this.permissions = new HashSet<>();
        this.permissions.addAll(permissions.stream().toList());
    }
    public void addSpecialPermissions(Set<SimpleGrantedAuthority> permissions){
        this.permissions.addAll(permissions.stream().toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}