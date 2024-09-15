package com.example.property.entity.communication;

import com.example.property.abstraction.AuditAble;
import com.example.property.entity.communication.Communication;
import com.example.property.enumuration.ChannelType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "communication_channel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommunicationChannel extends AuditAble<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communication_id")
    @JsonIgnore
    private Communication communication;

}
