package ru.app.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.application.entity.Task;
import ru.app.application.entity.TaskClass;
import ru.app.application.repository.TaskClassRepository;
import ru.app.application.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskClassRepository taskClassRepository;

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByClass(TaskClass taskClass) {
        return taskRepository.findByTaskClass(taskClass);
    }

    public List<Task> getTasksByClassId(Long taskClassId) {
        return taskRepository.findByTaskClassId(taskClassId);
    }

    public List<Task> getTasksByClassName(String className) {
        return taskRepository.findByTaskClass_ClassName(className);
    }
}
