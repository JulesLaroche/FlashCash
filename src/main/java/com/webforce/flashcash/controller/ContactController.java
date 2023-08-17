package com.webforce.flashcash.controller;

import com.webforce.flashcash.service.LinkService;
import com.webforce.flashcash.service.form.AddContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("addFriend")
public class ContactController {
    private final LinkService linkService;

    public ContactController(LinkService linkservice) {this.linkService = linkservice; }

    @GetMapping()
    public ModelAndView getAddConnectionForm(Model model) {
        return new ModelAndView("addFriend","addContactForm",new AddContactForm());
    }
    @PostMapping()
    public ModelAndView addConnection(@ModelAttribute("addContactForm") AddContactForm form, Model model) {
        try {
            linkService.addLink(form);
            // Ajoutez un message de succès à votre modèle si nécessaire
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return new ModelAndView("addFriend", model.asMap());
    }
}
