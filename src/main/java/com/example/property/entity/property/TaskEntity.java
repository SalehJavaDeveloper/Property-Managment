package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.entity.user.User;
import com.example.property.enumuration.TaskType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class TaskEntity extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private PropertyEntity propertyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private BuildingEntity buildingEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @JsonIgnore
    private UnitEntity unitEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "user_id")
    private User assignee;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Temporal(TIMESTAMP)
    private Date dueDataTime;

    private String subject;

    private String description;

}
