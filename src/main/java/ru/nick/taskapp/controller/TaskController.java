package ru.nick.taskapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.service.TaskService;

import java.util.List;
import java.util.Set;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/task")
    public String taskEntryPoint(@AuthenticationPrincipal User user, Model model) {

        List<Task> tasks = taskService.userTasks(user);
        if(tasks != null) {
            model.addAttribute("tasks", tasks);
        }

        return "task";
    }

    @PostMapping("/task")
    public String addTaskToUser(@AuthenticationPrincipal User user,
                                @RequestParam String name,
                                @RequestParam String definition){
        taskService.addTaskToUser(user,name,definition);

        return "task";
    }

}
