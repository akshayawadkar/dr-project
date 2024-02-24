package org.awlabs.dr.project.controller;

import java.util.List;

import org.awlabs.dr.project.model.User;
import org.awlabs.dr.project.repository.UserRepository;
import org.awlabs.dr.project.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;
    private static final Logger LOG = LogManager.getLogger("AppController");


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        LOG.info("Registering new user");
        Long newUser = userService.registerNewUser(user);
        if (newUser != null) {
            LOG.info("New User Registered Successfully with email: {}", user.getEmail());
        }
        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
}
