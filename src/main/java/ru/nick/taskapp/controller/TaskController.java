package ru.nick.taskapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.service.TaskService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Slf4j
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
        return "redirect:/task";
    }

    @PostMapping("/task/delete")
    public String deleteTaskFromUser2(@AuthenticationPrincipal User user, @RequestParam String id){
        log.info("Delete task with id {} from user {} username {}", id, user.getId(),user.getUsername());
        taskService.deleteTaskFromUser(user, Long.valueOf(id));
        return "redirect:/task";
    }

    @GetMapping("/task/getJson")
    public @ResponseBody String toJsonObject(@AuthenticationPrincipal User user){
        return taskService.userTasksAsJson(user);
    }

    @GetMapping("/task/download")
    public ResponseEntity<byte[]> downloadJson(@AuthenticationPrincipal User user){
        byte[] buf = taskService.userTaskAsBinary(user);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"mytask.json\"")
                .contentLength(buf.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(buf);
    }
}
