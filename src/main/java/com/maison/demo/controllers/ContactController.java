package com.maison.demo.controllers;

import com.maison.demo.models.Contact;
import com.maison.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

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
            return "contact.html";
        }
        this.contactService.create(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/messages")
    public ModelAndView showMessages(Model model) {
        List<Contact> messages = contactService.searchWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @GetMapping("/messages/{id}/close")
    public String closeMessage(@PathVariable Integer id, Authentication authentication) throws Exception {
        // contactService.updateStatus(id, authentication.getName());
        contactService.updateStatus(id);
        return "redirect:/messages";
    }

}
