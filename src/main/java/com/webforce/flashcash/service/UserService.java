package com.webforce.flashcash.service;

import com.webforce.flashcash.model.User;
import com.webforce.flashcash.model.UserAccount;
import com.webforce.flashcash.repository.UserAccountRepository;
import com.webforce.flashcash.repository.UserRepository;
import com.webforce.flashcash.service.form.AddAmountForm;
import com.webforce.flashcash.service.form.AddIbanForm;
import com.webforce.flashcash.service.form.SignUpForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, SessionService sessionService, UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
        this.userAccountRepository = userAccountRepository;
    }

    public User registration(SignUpForm form) {
        User user = new User();
        UserAccount account = new UserAccount();
        account.setAccount(0);
        user.setAccount(account);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return userRepository.save(user);

    }

    public void addIban(final AddIbanForm form){
        UserAccount account = sessionService.sessionUser().getAccount();
        account.setIban(form.getIban());

        userAccountRepository.save(account);
    }

    public void addAmount(final AddAmountForm form){
        UserAccount account = sessionService.sessionUser().getAccount();
        account.plus(form.getAmount());

        userAccountRepository.save(account);
    }

    public void minAmount(final AddAmountForm form){
        UserAccount account = sessionService.sessionUser().getAccount();
        account.minus(form.getAmount());

        userAccountRepository.save(account);
    }


}
