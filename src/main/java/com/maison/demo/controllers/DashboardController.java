package com.maison.demo.controllers;

import com.maison.demo.models.User;
import com.maison.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) throws Exception {
        User user = userService.findByEmail(authentication.getName());
        model.addAttribute("username", user.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loginUser", user);
        return "dashboard.html";
    }

}