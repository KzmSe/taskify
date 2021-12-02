package com.taskify.task.repository;

import com.taskify.task.entity.Task;
import com.taskify.task.entity.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByIdAndStatus(Long taskId, TaskStatus status);
}
