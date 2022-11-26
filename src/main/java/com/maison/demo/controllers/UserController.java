package com.maison.demo.controllers;

import com.maison.demo.models.User;
import com.maison.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "users/index.html";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "users/create.html";
    }

    @PostMapping()
    public String store(@Valid @ModelAttribute("user") User user, Errors errors) {
        if (errors.hasErrors()) {
            return "users/create.html";
        }
        if (userService.create(user)) {
            return "redirect:/login?register=true";
        }
        return "users/create.html";
    }

    @GetMapping(":id")
    public String show(Integer id) {
        return "users/detail.html";
    }

    @GetMapping(":id/edit")
    public String edit(Integer id) {
        return "users/edit.html";
    }

    @PutMapping(":id")
    public boolean update(Integer id) {
        return true;
    }

    @DeleteMapping(":id")
    public boolean destroy(Integer id) {
        return true;
    }
}
