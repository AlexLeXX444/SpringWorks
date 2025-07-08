package ru.app.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.app.application.entity.Task;
import ru.app.application.entity.TaskClass;
import ru.app.application.service.TaskClassService;
import ru.app.application.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskClassService taskClassService;

    @GetMapping("list")
    public String allTaskList(Model model) {
        List<Task> taskList = taskService.getAllTasks();
        model.addAttribute("tasks", taskList);
        return "all_task_list";
    }

    @GetMapping("add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add_task";
    }

    @GetMapping("list/select")
    public String selectedTaskList() {
        return "task_list_select";
    }

    @PostMapping("add")
    public String addTask(Task task) {
        TaskClass notStartedClass = taskClassService.getTaskClassByClassName("NOT STARTED");

        task.setTaskClass(notStartedClass);
        task.setCreatedAt(LocalDateTime.now());

        taskService.createTask(task);
        return "redirect:/task/list";
    }

    @GetMapping("list/select/closed")
    public String getClosedTasks(Model model) {
        String title = "CLOSED";
        model.addAttribute("tasks", taskService.getTasksByClassName(title));
        model.addAttribute("status", title);
        return "tasks_by_status";
    }

    @GetMapping("list/select/in_progress")
    public String getInProgressTasks(Model model) {
        String title = "IN PROGRESS";
        model.addAttribute("tasks", taskService.getTasksByClassName(title));
        model.addAttribute("status", title);
        return "tasks_by_status";
    }

    @GetMapping("list/select/not_started")
    public String getNotStartedTasks(Model model) {
        String title = "NOT STARTED";
        model.addAttribute("tasks", taskService.getTasksByClassName(title));
        model.addAttribute("status", title);
        return "tasks_by_status";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id).get();
        List<TaskClass> allClasses = taskClassService.getAllTaskClasses();

        model.addAttribute("task", task);
        model.addAttribute("allTaskClasses", allClasses);

        return "edit_task";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/task/list";
    }
}
