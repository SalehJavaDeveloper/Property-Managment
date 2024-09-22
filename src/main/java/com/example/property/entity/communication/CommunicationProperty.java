package com.example.property.entity.communication;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.property.PropertyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "communication_property")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommunicationProperty extends AuditAble<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "property_id")
    //    @JsonIgnore
    //    private PropertyEntity propertyEntity;

    @ManyToMany
    @JoinTable(
            name = "communication_prop_property",
            joinColumns = @JoinColumn(name = "com_property_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<PropertyEntity> propertyEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communication_id")
    @JsonIgnore
    private Communication communication;

}
