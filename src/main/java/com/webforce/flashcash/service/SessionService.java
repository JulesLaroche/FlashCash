package com.webforce.flashcash.service;


import com.webforce.flashcash.model.User;
import com.webforce.flashcash.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class SessionService {
    private final UserRepository userRepository;

    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User sessionUser() {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepository.findUserByMail(springUser.getUsername())
                .orElseThrow( () -> new RuntimeException("user with mail not found"));
        System.out.println(u.toString());
        return u;
    }

}