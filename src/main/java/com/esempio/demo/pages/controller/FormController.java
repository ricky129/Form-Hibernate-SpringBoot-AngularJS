package com.esempio.demo.pages.controller;

import com.esempio.demo.pages.entity.User;
import com.esempio.demo.pages.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FormController {

    private final UserService userService;

    @Autowired
    public FormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String test() {
        return "test";
    }
    
    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/save")
    public RedirectView saveUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email, @RequestParam String password)) {
        User user = new User(id, name, email);
        userService.saveUser(user);
        return new RedirectView("/success"); // Redirect to a success page
    }
    
    @GetMapping("/success")
    public String success() {
        return "success"; // This should match the name of the Thymeleaf template (success.html)
    }
}
