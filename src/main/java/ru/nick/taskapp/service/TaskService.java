package ru.nick.taskapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
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
    public void deleteTaskFromUser(User user, Long id){
        log.info("user {} wanna delete task {}",user.getUsername(), id);
        taskRepository.deleteTaskById(id);
    }
    public String userTasksAsJson(User user){
        List<Task> tasksByUser = taskRepository.findByUser(user);
        ObjectMapper mapper = new ObjectMapper();
        if(tasksByUser != null) {
            try {
                final String jsonTask = mapper.writeValueAsString(tasksByUser);
                log.info("User {} have json object {}", user.getUsername(),jsonTask);
                return jsonTask;
            } catch (JsonProcessingException e) {
                log.debug(Arrays.toString(e.getStackTrace()));
            }
        }
        else{
            log.info("user {} don't have any tasks", user.getUsername());
        }
        return null;
    }
    public byte[] userTaskAsBinary(User user){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsBytes(taskRepository.findByUser(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


}
