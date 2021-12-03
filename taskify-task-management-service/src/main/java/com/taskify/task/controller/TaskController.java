package com.taskify.task.controller;

import com.taskify.task.controller.dto.task.TaskCreationRequest;
import com.taskify.task.controller.dto.task.TaskResponse;
import com.taskify.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(value = "Operations related to organization in Taskify Task Management Service")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    @ApiOperation(value = "Create a new task")
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskCreationRequest request) {
        return ResponseEntity.ok(taskService.create(request));
    }

    @PostMapping("/tasks/{taskId}/accounts/{accountId}")
    @ApiOperation(value = "Assign task to account")
    public ResponseEntity<Boolean> assignToAccount(@PathVariable(name = "taskId") Long taskId,
                                                   @PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(taskService.assignToAccount(taskId, accountId));
    }

    @GetMapping("/organizations/{organizationId}/tasks")
    @ApiOperation(value = "Find all tasks by organization id")
    public ResponseEntity<List<TaskResponse>> findAllByOrganization(@PathVariable(name = "organizationId") Long organizationId) {
        return ResponseEntity.ok(taskService.findAllByOrganization(organizationId));
    }

    @GetMapping("/tasks/{taskId}/accounts/{accountId}")
    @ApiOperation(value = "Assign task to account")
    public ResponseEntity<List<TaskResponse>> findAllByAccount(@PathVariable(name = "organizationId") Long organizationId,
                                                               @PathVariable(name = "taskId") Long taskId) {
        return ResponseEntity.ok(taskService.findAllByAccount(organizationId, taskId));
    }
}
