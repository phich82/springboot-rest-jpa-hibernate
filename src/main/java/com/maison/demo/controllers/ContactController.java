package com.maison.demo.controllers;

import com.maison.demo.models.Contact;
import com.maison.demo.services.ContactService;
import com.maison.demo.services.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public String index(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

//    @PostMapping("/saveMsg")
//    public ModelAndView store(
//            @RequestParam String name,
//            @RequestParam String mobileNum,
//            @RequestParam String email,
//            @RequestParam String subject,
//            @RequestParam String message) {
//
//        Log.info(this,"name: " + name);
//        Log.info(this, "mobileNum: " + mobileNum);
//        Log.info(this, "email: " + email);
//        Log.info(this, "subject: " + subject);
//        Log.info(this, "message: " + message);
//
//        return new ModelAndView("redirect:/contacts");
//    }
    @PostMapping("/saveMsg")
    public String store(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            Log.error(this, "[saveMsg][errors]" + errors.toString());
            return "contact.html";
        }
        this.contactService.create(contact);
        this.contactService.setCounter(1);
        Log.info(this, "Counter: " + this.contactService.getCounter());
        return "redirect:/contacts";
    }

//    @PostMapping("/saveMsg")
//    public ModelAndView store(Contact contact) {
//        this.contactService.create(contact);
//        return new ModelAndView("redirect:/contacts");
//    }
}
