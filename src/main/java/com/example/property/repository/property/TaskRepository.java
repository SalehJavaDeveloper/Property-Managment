package com.example.property.repository.property;

import com.example.property.entity.property.TaskEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.enumuration.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long>, JpaSpecificationExecutor<TaskEntity> {
    List<TaskEntity> getTaskEntitiesByTaskStatus(TaskStatus taskStatus);
}
