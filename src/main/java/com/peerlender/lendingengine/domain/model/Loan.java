package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User borrower;
    @ManyToOne
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

    public int getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public User getBorrower() {
        return borrower;
    }

    public User getLender() {
        return lender;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public LocalDate getDateLent() {
        return dateLent;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }
}
