package ru.app.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_text", nullable = false)
    private String taskText;

    @ManyToOne
    @JoinColumn(name = "task_class_id", nullable = false)
    private TaskClass taskClass;

    @Column(name = "task_created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", taskText='" + taskText + "', taskClass=" + taskClass.getId() + "}";
    }
}
