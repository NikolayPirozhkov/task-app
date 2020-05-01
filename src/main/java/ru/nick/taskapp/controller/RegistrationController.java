package ru.nick.taskapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.nick.taskapp.service.UserService;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

}
