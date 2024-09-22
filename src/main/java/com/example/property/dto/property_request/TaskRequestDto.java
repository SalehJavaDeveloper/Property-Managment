package com.example.property.dto.property_request;

import com.example.property.enumuration.TaskStatus;
import com.example.property.enumuration.TaskType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {

    @NotNull(message = "Property Id not be Null")
    private Long propertyId;

    private Long BuildingId;

    private Long unitId;

    @NotNull(message = "Assignee Id not be Null")
    private Long assigneeId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Task type not be Null")
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Task status not be Null")
    private TaskStatus taskStatus;

    @NotNull(message = "Due Date Time not be Null")
    private Date dueDataTime;

    @NotNull(message = "Subject not be Null")
    private String subject;

    private String description;

}
