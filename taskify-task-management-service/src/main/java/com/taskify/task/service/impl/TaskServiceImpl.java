package com.taskify.task.service.impl;

import com.taskify.task.client.AccountClient;
import com.taskify.task.controller.dto.task.TaskCreationRequest;
import com.taskify.task.controller.dto.task.TaskResponse;
import com.taskify.task.entity.Task;
import com.taskify.task.entity.TaskAccountCollection;
import com.taskify.task.entity.enums.TaskStatus;
import com.taskify.task.exception.DataNotFoundException;
import com.taskify.task.exception.response.ResponseMessage;
import com.taskify.task.mapper.TaskMapper;
import com.taskify.task.repository.AccountCollectionRepository;
import com.taskify.task.repository.TaskRepository;
import com.taskify.task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AccountCollectionRepository accountCollectionRepository;
    private final AccountClient accountClient;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, AccountCollectionRepository accountCollectionRepository, AccountClient accountClient) {
        this.taskRepository = taskRepository;
        this.accountCollectionRepository = accountCollectionRepository;
        this.accountClient = accountClient;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskResponse create(TaskCreationRequest request) {
        var task = TaskMapper.INSTANCE.creationRequestToEntity(request);
        task.setStatus(TaskStatus.OPEN);
        task.setOrganizationId(request.getOrganizationId());

        var savedTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.entityToTaskResponse(savedTask);
    }

    @Override
    public Boolean assign(String authHeader, Long taskId, Long accountId) {
        var isAssignmentExist = accountCollectionRepository.existsByTask_IdAndAccountId(taskId, accountId);
        if (isAssignmentExist) {
            throw new DataIntegrityViolationException(ResponseMessage.ERROR_TASK_IS_ALREADY_ASSIGNED_TO_ACCOUNT);
        }

        var isAccountExist = accountClient.isAccountExist(authHeader, accountId);
        if (isAccountExist) {
            throw new DataNotFoundException(ResponseMessage.ERROR_ACCOUNT_NOT_FOUND_BY_ID);
        }

        TaskAccountCollection collection = new TaskAccountCollection();
        collection.setTask(Task.builder().id(taskId).build());
        collection.setAccountId(accountId);

        var savedCollection = accountCollectionRepository.save(collection);
        return savedCollection.getId() != null;
    }

    @Override
    public List<TaskResponse> findAllByOrganization(Long organizationId) {
        var taskList = taskRepository.findByOrganizationId(organizationId);
        return TaskMapper.INSTANCE.entityListToResponseList(taskList);
    }

    @Override
    public List<TaskResponse> findAllByAccount(Long organizationId, Long accountId) {
        var taskList = taskRepository.findByAccountIdAndOrganizationId(accountId, organizationId);
        return TaskMapper.INSTANCE.entityListToResponseList(taskList);
    }
}
