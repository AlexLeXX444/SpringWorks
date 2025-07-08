package ru.app.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.app.application.service.TaskService;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @PostMapping("/delete")
    public String deleteTask(@RequestParam("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/task/list";
    }
}
