package com.taskify.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_account_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskAccountCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_task_id")
    private Task task;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "organization_id")
    private Long organizationId;
}
