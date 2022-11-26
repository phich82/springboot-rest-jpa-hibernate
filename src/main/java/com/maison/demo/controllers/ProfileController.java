package com.maison.demo.controllers;

import com.maison.demo.models.Profile;
import com.maison.demo.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public ModelAndView index(Model model, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("profile.html");
        User user = (User) session.getAttribute("loginUser");
        Profile profile = new Profile();
        profile.setName(user.getName());
        profile.setMobileNumber(user.getMobileNumber());
        profile.setEmail(user.getEmail());
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping
    public String store() {
        return "redirect:/profile";
    }
}
