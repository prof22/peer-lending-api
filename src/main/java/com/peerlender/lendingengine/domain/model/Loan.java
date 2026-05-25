package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private long id;
    private User borrower;
    private User lender;
    private int amount;
    private double interestRate;
    private LocalDate dateLent;
    private LocalDate dateDue;

    public Loan(){

    }

    public Loan(User lender, LoanApplication loanapplication){
        this.borrower = loanapplication.getBorrower();
        this.lender = lender;
        this.amount = loanapplication.getAmount();
        this.interestRate = loanapplication.getInterestRate();
        this.dateLent = LocalDate.now();
        this.dateDue = LocalDate.now().plusDays(loanapplication.getRepaymentTermInDays());
    }
}
