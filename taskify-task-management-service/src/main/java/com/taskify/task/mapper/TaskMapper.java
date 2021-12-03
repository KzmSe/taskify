package com.taskify.task.mapper;

import com.taskify.task.controller.dto.task.TaskCreationRequest;
import com.taskify.task.controller.dto.task.TaskResponse;
import com.taskify.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task creationRequestToEntity(TaskCreationRequest request);

    TaskResponse entityToTaskResponse(Task task);

    List<TaskResponse> entityListToResponseList(List<Task> taskList);
}
