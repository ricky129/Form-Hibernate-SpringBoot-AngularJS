package com.esempio.demo.pages.controller;

import com.esempio.demo.pages.entity.User;
import com.esempio.demo.pages.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    private final UserService userService;

    @Autowired
    public FormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/form")
    public String showForm() {
        return "form"; // Assuming you have a form.html template in your resources/templates directory
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email) {
        User user = new User(id, name, email);
        userService.saveUser(user);
        return "redirect:/success"; // Redirect to a success page
    }
}
