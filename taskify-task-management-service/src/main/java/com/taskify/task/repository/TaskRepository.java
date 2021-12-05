package com.taskify.task.repository;

import com.taskify.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOrganizationId(Long organizationId);

    @Query("SELECT t FROM Task t LEFT JOIN t.accountCollection ac WHERE t.organizationId = ?1 AND ac.accountId = ?2")
    List<Task> findByAccountIdAndOrganizationId(Long accountId, Long organizationId);
}
