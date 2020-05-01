package ru.nick.taskapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nick.taskapp.entity.Role;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.repository.UserRepository;

import java.util.Collections;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean registrationUser(User user){

        if(userRepository.findByUsername(user.getUsername()) != null){
            return false;
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
        log.info("User {} successful registered with password {}", user.getUsername(),user.getPassword());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
