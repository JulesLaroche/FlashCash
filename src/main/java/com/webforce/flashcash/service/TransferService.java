package com.webforce.flashcash.service;

import com.webforce.flashcash.model.Transfer;
import com.webforce.flashcash.repository.UserAccountRepository;
import com.webforce.flashcash.repository.UserRepository;
import com.webforce.flashcash.service.form.TransferForm;
import com.webforce.flashcash.model.User;
import com.webforce.flashcash.model.Transfer;
import com.webforce.flashcash.repository.TransferRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {
    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;
    private final TransferRepository transferRepository;

    public TransferService(UserRepository userRepository, SessionService sessionService, UserAccountRepository userAccountRepository, TransferRepository transferRepository) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.userAccountRepository = userAccountRepository;
        this.transferRepository = transferRepository;
    }

    public void transfer(TransferForm form) {
        if (form != null) {
            User to = userRepository.findUserByMail(form.getContactEmail())
                    .orElseThrow(()-> new RuntimeException("User with email not found"));
            Transfer transfer = new Transfer();
            transfer.setDate(LocalDateTime.now());
            transfer.setAmountBeforeFee(form.getAmount());
            transfer.setAmountAfterFee(form.getAmount() + form.getAmount() * 0.005);
            transfer.setFrom(sessionService.sessionUser());
            transfer.setTo(to);

            userAccountRepository.save(sessionService.sessionUser().getAccount().minus(transfer.getAmountAfterFee()));
            userAccountRepository.save(to.getAccount().plus(transfer.getAmountBeforeFee()));
            transferRepository.save(transfer);
        } else {

        }
    }

    public List<Transfer> findTransactions() {
        User currentUser = sessionService.sessionUser();
        return transferRepository.findAllByFromId(currentUser.getId());
    }
}
