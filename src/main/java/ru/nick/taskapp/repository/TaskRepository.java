package ru.nick.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nick.taskapp.entity.Task;
import ru.nick.taskapp.entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Transactional
    List<Task> findByUser(User user);
    Set<Task> findByUserId(Long id);
}
