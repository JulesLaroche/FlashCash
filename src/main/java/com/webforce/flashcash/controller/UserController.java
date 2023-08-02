package com.webforce.flashcash.controller;

import com.webforce.flashcash.repository.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }
}


