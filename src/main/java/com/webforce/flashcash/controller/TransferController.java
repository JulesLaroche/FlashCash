package com.webforce.flashcash.controller;

import com.webforce.flashcash.model.Transfer;
import com.webforce.flashcash.service.LinkService;
import com.webforce.flashcash.service.SessionService;
import com.webforce.flashcash.service.TransferService;
import com.webforce.flashcash.service.UserService;
import com.webforce.flashcash.service.form.TransferForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransferController {

    private final TransferService transferService;
    private final LinkService linkservice;
    private final UserService userservice;
    private final SessionService sessionService;

    public TransferController(TransferService transferService, LinkService linkService, UserService userService, SessionService sessionService){
        this.transferService = transferService;
        this.linkservice = linkService;
        this.userservice = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("transfer")
    public ModelAndView transfer(Model model) {
        List<Transfer> transfers = transferService.findTransactions();
        System.out.println("Transfers retrieved: " + transfers);
        model.addAttribute("transfers", transfers);
        return new ModelAndView("transfer");
    }

    @GetMapping("transfer-to-contact")
    public ModelAndView transferToContact(Model model){
        List<String> linksEmail = linkservice.findLinkEmail();
        model.addAttribute("linksEmail", linksEmail);
        return new ModelAndView("transfer-to-contact", "transferForm", new TransferForm());
    }


    @PostMapping("transfer-to-contact")
    public ModelAndView transfer(Model model, @ModelAttribute("transferForm") TransferForm form){
        transferService.transfer(form);
        List<Transfer> transfers = transferService.findTransactions();
        System.out.println("Transfers retrieved: " + transfers);
        model.addAttribute("transfers", transfers);
        return new ModelAndView("transfer");
    }


}
