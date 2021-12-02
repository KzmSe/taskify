package com.taskify.task.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AccountCollectionRepository accountCollectionRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, AccountCollectionRepository accountCollectionRepository) {
        this.taskRepository = taskRepository;
        this.accountCollectionRepository = accountCollectionRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskResponse create(TaskCreationRequest request) {
        var task = TaskMapper.INSTANCE.creationRequestToEntity(request);
        task.setStatus(TaskStatus.OPEN);

        var savedTask = taskRepository.save(task);
        var taskResponse = TaskMapper.INSTANCE.entityToTaskResponse(savedTask);

        var isTaskAssigned = assignToOrganization(savedTask.getId(), request.getOrganizationId());
        if (!isTaskAssigned) {
            //throw exception
        }

        taskResponse.setOrganizationId(request.getOrganizationId());
        return taskResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignToOrganization(Long taskId, Long organizationId) {
        var isTaskExist = taskRepository.existsByIdAndStatus(taskId, TaskStatus.OPEN);
        if (!isTaskExist) {
            throw new DataNotFoundException(ResponseMessage.ERROR_TASK_NOT_FOUND_BY_ID);
        }

        TaskAccountCollection collection = new TaskAccountCollection();
        collection.setOrganizationId(organizationId);
        Task task = new Task();
        task.setId(taskId);
        collection.setTask(task);

        var savedCollection = accountCollectionRepository.save(collection);
        return savedCollection.getId() != null;
    }
}
