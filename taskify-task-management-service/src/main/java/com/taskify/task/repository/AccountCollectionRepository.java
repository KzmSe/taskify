package com.taskify.task.repository;

import com.taskify.task.entity.TaskAccountCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountCollectionRepository extends JpaRepository<TaskAccountCollection, Long> {

    Boolean existsByTask_IdAndAccountId(Long taskId, Long accountId);
}
