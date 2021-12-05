package com.taskify.task.entity;

import com.taskify.task.entity.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "organization_id")
    private Long organizationId;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Set<TaskAccountCollection> accountCollection = new HashSet<>();
}
