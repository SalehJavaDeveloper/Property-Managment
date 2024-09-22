package com.example.property.service.impl.property_impl;

import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.TaskRequestDto;
import com.example.property.dto.property_response.TaskResponseDto;
import com.example.property.dto.property_request.TaskUpdateRequestDto;
import com.example.property.dto.property_response.UnitResponseDto;
import com.example.property.entity.property.TaskEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.enumuration.TaskStatus;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.mapper.property.TaskMapper;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.repository.property.TaskRepository;
import com.example.property.repository.property.UnitRepository;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.property.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final PropertyRepository propertyRepository;
    private final BuildingRepository buildingRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final FilterSpecification<TaskEntity> taskEntityFilterSpecification;


    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository, PropertyRepository propertyRepository, BuildingRepository buildingRepository, UnitRepository unitRepository, UserRepository userRepository, FilterSpecification<TaskEntity> taskEntityFilterSpecification) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.propertyRepository = propertyRepository;
        this.buildingRepository = buildingRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;

        this.taskEntityFilterSpecification = taskEntityFilterSpecification;
    }

    @Override
    public void create(TaskRequestDto requestDto) throws MethodArgumentNotValidException {
        TaskEntity taskEntity = taskMapper.mapRequestDtoToEntity(requestDto);
        taskEntity.setAssignee(userRepository.findById(requestDto.getAssigneeId()).orElseThrow(() -> new MethodArgumentNotValidException("UerId(Assignee) is not Valid")));
        taskEntity.setPropertyEntity(propertyRepository.findById(requestDto.getPropertyId()).orElseThrow(() -> new MethodArgumentNotValidException("PropertyID is not Valid")));
        taskEntity.setBuildingEntity(buildingRepository.findById(requestDto.getBuildingId()).orElseThrow(() -> new MethodArgumentNotValidException("BuildingID is not Valid")));
        taskEntity.setUnitEntity(unitRepository.findById(requestDto.getUnitId()).orElseThrow(() -> new MethodArgumentNotValidException("UnitId is not Valid")));

        taskRepository.save(taskEntity);
    }

    @Override
    public Page<TaskResponseDto> getAll(int pageNumber,int pageSize) {

        Pageable pageAble = PageRequest.of(pageNumber, pageSize);

        Page<TaskEntity> taskEntities = taskRepository.findAll(pageAble);
        Page<TaskResponseDto> taskResponseDtos = taskMapper.mapPageEntityToPageResponse(taskEntities);
        return taskResponseDtos;
    }

    @Override
    public TaskResponseDto findById(Long id) throws MethodArgumentNotValidException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        return taskMapper.mapEntityToResponseDto(taskEntity);
    }

    @Override
    public void delete(Long id) throws MethodArgumentNotValidException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        taskRepository.delete(taskEntity);
    }

    @Override
    public void update(Long id, TaskUpdateRequestDto updateRequestDto) throws MethodArgumentNotValidException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        taskMapper.update(taskEntity, updateRequestDto);
        taskRepository.save(taskEntity);
    }

    @Override
    public Page<TaskResponseDto> getSpecification(RequestDto requestDto) {
        Specification<TaskEntity> taskEntitySpecification = taskEntityFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return taskMapper.mapPageEntityToPageResponse(taskRepository.findAll(taskEntitySpecification, pageable));
    }
    public int completedTaskNum() {
        List<TaskEntity> taskEntitiesByTaskStatus = taskRepository.getTaskEntitiesByTaskStatus(TaskStatus.DONE);

        return taskEntitiesByTaskStatus.size();
    }
}
