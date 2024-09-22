package com.example.property.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserPosition {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
}
