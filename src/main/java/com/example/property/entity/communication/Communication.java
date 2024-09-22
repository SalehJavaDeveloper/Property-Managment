package com.example.property.entity.communication;

import com.example.property.abstraction.AuditAble;
import com.example.property.enumuration.MessageStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "communication")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Communication extends AuditAble<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDate deliveryDate;

    private String subject;
    private String content;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private MessageStatus messageStatus;

    @OneToMany(mappedBy = "communication")
    @JsonIgnore
    private List<CommunicationChannel> communicationChannels;

    @OneToMany(mappedBy = "communication")
    @JsonIgnore
    private List<CommunicationProperty> communicationProperties;

    @OneToMany(mappedBy = "communication")
    @JsonIgnore
    private List<CommunicationBuilding> communicationBuildings;

    @OneToMany(mappedBy = "communication")
    @JsonIgnore
    private List<CommunicationUnit> communicationUnits;

    @Column(name = "task_id")
    private String taskId;
}
