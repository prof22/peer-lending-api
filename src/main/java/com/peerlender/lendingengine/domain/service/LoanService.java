package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.domain.exception.LoanApplicationNotFoundException;
import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.LoanRepostory;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanRepostory loanRepostory;

    @Autowired
    public LoanService(LoanApplicationRepository loanApplicationRepository,
                       UserRepository userRepository,
                       LoanRepostory loanRepostory) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanRepostory = loanRepostory;
    }

    public void acceptLoan(final long loanApplicationId, final long leaderId){
        User leader = userRepository.findById(leaderId)
                .orElseThrow(() -> new UserNotFoundException(leaderId));

        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
                .orElseThrow(() -> new LoanApplicationNotFoundException(loanApplicationId));
        loanRepostory.save(new Loan(leader, loanApplication));
    }

    public List<Loan> getLoans(){
        return loanRepostory.findAll();
    }
}
