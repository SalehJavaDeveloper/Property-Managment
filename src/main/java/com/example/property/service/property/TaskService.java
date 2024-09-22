package com.example.property.service.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.TaskRequestDto;
import com.example.property.dto.property_response.TaskResponseDto;
import com.example.property.dto.property_request.TaskUpdateRequestDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;


public interface TaskService {

    void create(TaskRequestDto requestDto) throws MethodArgumentNotValidException;

    Page<TaskResponseDto> getAll(int pageNumber, int pageSize);

    TaskResponseDto findById(Long id) throws MethodArgumentNotValidException;

    void delete(Long id) throws MethodArgumentNotValidException;

    void update(Long id, TaskUpdateRequestDto updateRequestDto) throws MethodArgumentNotValidException;
    Page<TaskResponseDto> getSpecification(RequestDto requestDto);

    int completedTaskNum();

}
