package com.example.property.entity.user;


import com.example.property.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usergrantedauthority")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    @NotEmpty(message = "The authority should not be empty!")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User mainUser;
}
