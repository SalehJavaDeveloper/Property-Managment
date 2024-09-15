package com.example.property.dto.property_response;


import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.entity.user.User;
import com.example.property.enumuration.TaskType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long id;

    private PropertyEntity propertyEntity;

    private BuildingEntity buildingEntity;

    private UnitEntity unitEntity;

    private User assignee;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDataTime;

    private String subject;

    private String description;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
