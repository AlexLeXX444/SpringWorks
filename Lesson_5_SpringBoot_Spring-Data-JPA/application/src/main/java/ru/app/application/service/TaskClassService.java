package ru.app.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.application.entity.TaskClass;
import ru.app.application.repository.TaskClassRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskClassService {

    private final TaskClassRepository taskClassRepository;

    public TaskClass getTaskClassByClassName(String className) {
        return taskClassRepository.findTaskClassByClassName(className);
    }

    public List<TaskClass> getAllTaskClasses() {
        return taskClassRepository.findAll();
    }
}
