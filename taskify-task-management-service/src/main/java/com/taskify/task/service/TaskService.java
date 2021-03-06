package com.taskify.task.service;

import com.taskify.task.controller.dto.task.TaskCreationRequest;
import com.taskify.task.controller.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse create(TaskCreationRequest request);

    Boolean assign(String authHeader, Long taskId, Long accountId);

    List<TaskResponse> findAllByOrganization(Long organizationId);

    List<TaskResponse> findAllByAccount(Long organizationId, Long accountId);
}
