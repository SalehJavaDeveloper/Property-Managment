package com.example.property.controller.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.TaskRequestDto;
import com.example.property.dto.property_response.TaskResponseDto;
import com.example.property.dto.property_request.TaskUpdateRequestDto;
import com.example.property.dto.property_response.UnitResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.impl.property_impl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
public class TaskContoller {

    private final TaskServiceImpl taskService;

    public TaskContoller(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get All Tasks")
    public ResponseEntity<SuccessDetails<Page<TaskResponseDto>>> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(new SuccessDetails<>(taskService.getAll(pageNumber, pageSize), HttpStatus.OK.value(), true));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Task By Id")
    public ResponseEntity<SuccessDetails<TaskResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new SuccessDetails<>(taskService.findById(id), HttpStatus.OK.value(), true));
    }

    @PostMapping
    @Operation(description = "Save operation",
            summary = "This is the summary for save task",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token", responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> create(@RequestBody @Valid TaskRequestDto requestDto) {
        taskService.create(requestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Task save Successfully!", HttpStatus.OK.value(), true));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Task")
    public ResponseEntity<SuccessDetails<String>> update(@PathVariable Long id, TaskUpdateRequestDto updateRequestDto) {
        taskService.update(id, updateRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Task update Successfully!", HttpStatus.OK.value(), true));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Task")
    public ResponseEntity<SuccessDetails<String>> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok(new SuccessDetails<>("Task deleted Successfully!", HttpStatus.OK.value(), true));
    }

    @PostMapping("/specification/task")
    @Operation(description = "Specification and filtering into task.")
    ResponseEntity<SuccessDetails<Page<TaskResponseDto>>> getUnitPage(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(new SuccessDetails<>(taskService.getSpecification(requestDto), HttpStatus.OK.value(), true));
    }

    @GetMapping("/completed-task")
    @Operation(summary = "Number of completed task")
    public ResponseEntity<SuccessDetails<Integer>> getCompletedTaskNumber() {
        return ResponseEntity.ok(new SuccessDetails<>(taskService.completedTaskNum(), HttpStatus.OK.value(), true));
    }
}
