package ru.nick.taskapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nick.taskapp.entity.User;
import ru.nick.taskapp.service.UserService;

import java.util.Map;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if(!userService.registrationUser(user)){
            model.addAttribute("message","User exist!");
            return "registration";
        }
        return "redirect:/login";

    }
}
