package ru.nick.taskapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nick.taskapp.entity.User;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@SpringBootTest
public class UserRepositoryTest {
    private final UserRepository userRepository;

    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Test
    void loadUserByUsername(String username) {
        User byUsername = userRepository.findByUsername(username);


    }
}
