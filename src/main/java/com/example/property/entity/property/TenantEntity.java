package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tenant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TenantEntity extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitEntity unitEntity;

    private String name;

    private String surname;

    private String fatherName;

    private String phoneNumber1;

    private String phoneNumber2;

    private String homeNumber;

    private Boolean wpPhoneNumber1;

    private Boolean callPhoneNumber1;

    private Boolean smsPhoneNumber1;

    private Boolean wpPhoneNumber2;

    private Boolean callPhoneNumber2;

    private Boolean smsPhoneNumber2;

    private String email;

    private String pin;

}