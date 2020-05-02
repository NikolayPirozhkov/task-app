package ru.nick.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;

import java.util.Set;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
public interface TaskRepository extends JpaRepository<Task,Long> {
    Set<Task> findByUser(User user);
}
