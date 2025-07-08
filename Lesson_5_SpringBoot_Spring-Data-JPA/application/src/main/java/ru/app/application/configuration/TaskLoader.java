package ru.app.application.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.app.application.entity.Task;
import ru.app.application.entity.TaskClass;
import ru.app.application.repository.TaskClassRepository;
import ru.app.application.service.TaskService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class TaskLoader implements CommandLineRunner {

    private final TaskService taskService;
    private final TaskClassRepository taskClassRepository;

    private static final List<String> TASK_TEMPLATES = List.of(
            "Complete %s task",
            "Review %s documentation",
            "Fix bug in %s module",
            "Prepare %s report",
            "Test %s integration",
            "Update %s settings",
            "Optimize %s performance",
            "Deploy %s environment",
            "Write unit tests for %s",
            "Coordinate with %s team"
    );

    private static final List<String> TASK_CLASS_TEMPLATES = List.of(
            "CLOSED",
            "IN PROGRESS",
            "NOT STARTED"
    );

    @Override
    public void run(String... args) throws Exception {
        if (taskClassRepository.findAll().isEmpty()) {
            for (String taskTemplate : TASK_CLASS_TEMPLATES) {
                TaskClass taskClass = new TaskClass();
                taskClass.setClassName(taskTemplate);
                taskClassRepository.save(taskClass);
            }
            System.out.println("[INFO] :: Task classes created.");
        }

        if (taskService.getAllTasks().isEmpty()) {
            List<TaskClass> taskClasses = taskClassRepository.findAll();
            List<Task> tasks = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                String randomText = generateRandomTaskText();
                TaskClass randomClass = getRandomTaskClass(taskClasses);

                Task task = new Task();
                task.setTaskText(randomText);
                task.setTaskClass(randomClass);
                task.setCreatedAt(LocalDateTime.now());

                tasks.add(task);
            }

            for (Task task : tasks) {
                taskService.createTask(task);
            }

            System.out.println("[INFO] :: Created " + tasks.size() + " random tasks.");
        }
    }

    private String generateRandomTaskText() {
        String placeholder = getRandomWord();
        int index = new Random().nextInt(TASK_TEMPLATES.size());
        return String.format(TASK_TEMPLATES.get(index), placeholder);
    }

    private String getRandomWord() {
        List<String> words = Arrays.asList("project", "module", "feature", "code", "report", "document", "system");
        return words.get(new Random().nextInt(words.size()));
    }

    private TaskClass getRandomTaskClass(List<TaskClass> taskClasses) {
        return taskClasses.get(ThreadLocalRandom.current().nextInt(taskClasses.size()));
    }
}
