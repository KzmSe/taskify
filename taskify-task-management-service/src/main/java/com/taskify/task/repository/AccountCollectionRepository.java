package com.taskify.task.repository;

import com.taskify.task.entity.TaskAccountCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCollectionRepository extends JpaRepository<TaskAccountCollection, Long> {

}
