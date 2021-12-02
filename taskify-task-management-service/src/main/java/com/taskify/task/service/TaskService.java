package com.taskify.task.service;

import com.taskify.task.controller.dto.task.TaskCreationRequest;
import com.taskify.task.controller.dto.task.TaskResponse;

public interface TaskService {

    TaskResponse create(TaskCreationRequest request);

    Boolean assignToOrganization(Long taskId, Long organizationId);

//    AccountResponse assign(AccountCreationRequest request);
//
//    Boolean findAllByStatus(Long organizationId, Long accountId);
//
//    Boolean findAllByIdAndStatus(Long accountId);
}
