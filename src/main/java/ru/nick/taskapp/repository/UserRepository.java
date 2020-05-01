package ru.nick.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nick.taskapp.entity.User;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
