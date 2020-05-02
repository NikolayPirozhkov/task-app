package ru.nick.taskapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Service
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public List<Task> userTasks(User user){
        log.info("user {} wanna tasks", user.getUsername());
        return taskRepository.findByUser(user);
    }

    public Task addTaskToUser(User user, String name, String definition){
        Task task = new Task();
        task.setName(name);
        task.setDefinition(definition);
        task.setUser(user);
        log.info("add task {} with definition {} to user {}", task.getName(),task.getDefinition(),user.getUsername());
        taskRepository.save(task);
        return task;
    }
    public Set<Task> userTasksById(User user){
        log.info("user{} with ID = {} wanna tasks by userId", user.getUsername(),user.getId());
        return taskRepository.findByUserId(user.getId());
    }


}
