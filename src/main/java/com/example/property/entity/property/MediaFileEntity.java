package com.example.property.entity.property;

import com.example.property.abstraction.AuditAble;
import com.example.property.enumuration.MediaFileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "media_file")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class MediaFileEntity extends AuditAble<Long> {

    @Id
    private UUID id;

    private String filePath;

    @Enumerated(EnumType.STRING)
    private MediaFileType mediaFileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "task_id")
    @JsonIgnore
    private TaskEntity taskEntity;

}
