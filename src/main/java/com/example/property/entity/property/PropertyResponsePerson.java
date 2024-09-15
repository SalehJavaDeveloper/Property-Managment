package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "property_responsible_person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PropertyResponsePerson extends AuditAble<Long>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private PropertyEntity propertyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_person_Id")
    private User user;
}
