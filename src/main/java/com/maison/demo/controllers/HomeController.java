package com.maison.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value={"", "/", "/home"})
    public String index(Model model) {
        model.addAttribute("username", "JHP Phich 82");
        return "home.html";
    }
}
