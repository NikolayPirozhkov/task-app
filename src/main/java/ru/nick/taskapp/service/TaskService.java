package ru.nick.taskapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.repository.TaskRepository;

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

    public Set<Task> userTasks(User user){

        return taskRepository.findByUser(user);
    }
}
