package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan("ru")
public class HelloController {
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public HelloController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @GetMapping("/")
    public String helloMessage(Model model) {
        List<String> messageList = new ArrayList<>();
        messageList.add("Hello, this is my first CRUD app\n");
        messageList.add("There you can show, add, edit and delete some phantom users\n");
        model.addAttribute("messages", messageList);
        return "index";
    }
    @GetMapping("/user")
    public String users(Model model, Principal principal) {
        User user = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
