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

//    @GetMapping("/organizations/{organizationId}/existence")
//    @ApiOperation(value = "Check organization with given id is exist")
//    public ResponseEntity<Boolean> isOrganizationExist(@PathVariable(name = "organizationId") Long organizationId) {
//        return ResponseEntity.ok(organizationService.isOrganizationExist(organizationId));
//    }
}
