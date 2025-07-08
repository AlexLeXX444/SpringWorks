package ru.app.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TaskClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_class_id")
    private long id;

    @Column(name = "task_class_name", unique = true, nullable = false)
    private String className;

    @OneToMany(mappedBy = "taskClass")
    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return "TaskClass{id=" + id + ", className='" + className + "}";
    }
}
