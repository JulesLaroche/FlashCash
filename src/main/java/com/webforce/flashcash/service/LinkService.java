package com.webforce.flashcash.service;

import com.webforce.flashcash.model.Link;
import com.webforce.flashcash.model.User;
import com.webforce.flashcash.repository.LinkRepostitory;
import com.webforce.flashcash.repository.UserRepository;
import com.webforce.flashcash.service.form.AddContactForm;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService {

    private final LinkRepostitory linkRepostitory;
    private final UserRepository userRepository;
    private final SessionService sessionService;

    public LinkService(LinkRepostitory linkRepostitory, UserRepository userRepository, SessionService sessionService){
        this.linkRepostitory = linkRepostitory;
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    public void addLink(AddContactForm form) {
        User user = userRepository
                .findUserByMail(form.getEmail())
                .orElseThrow(() -> new RuntimeException("User with email " + form.getEmail() + " not found"));
        User connectedUser = userRepository.findUserByMail(sessionService.sessionUser().getEmail())
                .orElseThrow(() -> new RuntimeException("User with email not found"));
        Link link = new Link();
        link.setUser1(connectedUser);
        link.setUser2(user);
        linkRepostitory.save(link);
    }

    public List<String> findLinkEmail(){
        return linkRepostitory.findLinkByUser1Email(sessionService.sessionUser().getEmail()).stream().map(Link::getUser2).map(User::getEmail).collect(Collectors.toList());
    }

    
}
