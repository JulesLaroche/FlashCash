package com.webforce.flashcash.controller;

import com.webforce.flashcash.model.User;
import com.webforce.flashcash.service.SessionService;
import com.webforce.flashcash.service.form.AddAmountForm;
import com.webforce.flashcash.service.form.AddIbanForm;
import com.webforce.flashcash.service.form.SignUpForm;
import com.webforce.flashcash.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }


    @GetMapping("/")
    public ModelAndView none(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("index");
    }



    @GetMapping("/profile")
    public ModelAndView profile(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

    @GetMapping("/contact")
    public ModelAndView contact(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("contact");
    }
    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signUpForm") SignUpForm form){
        userService.registration(form);
        return new ModelAndView("signin");
    }

    @GetMapping("/iban")
    public ModelAndView getAddConnectionForm(Model model) {
        User user = sessionService.sessionUser();
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return new ModelAndView("iban", "addIbanForm", new AddIbanForm());
    }

    @PostMapping("/iban")
    public ModelAndView addIban(Model model, @ModelAttribute("addIbanForm") AddIbanForm form){
        userService.addIban(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

    @GetMapping("/addCash")
    public ModelAndView getAddAmountForm(Model model) {
        User user = sessionService.sessionUser();
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return new ModelAndView("addCash", "addAmountForm", new AddAmountForm());
    }

    @PostMapping("/addCash")
    public ModelAndView addAmount(Model model, @ModelAttribute("addAmountForm") AddAmountForm form){
        userService.addAmount(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

    @GetMapping("/minCash")
    public ModelAndView getMinAmountForm(Model model) {
        User user = sessionService.sessionUser();
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return new ModelAndView("minCash", "addAmountForm", new AddAmountForm());
    }

    @PostMapping("/minCash")
    public ModelAndView minAmount(Model model, @ModelAttribute("addAmountForm") AddAmountForm form){
        userService.minAmount(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }


    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.invalidate(); // Invalidate the user's session
        return "redirect:/"; // Redirect to the home page after sign out
    }


}


