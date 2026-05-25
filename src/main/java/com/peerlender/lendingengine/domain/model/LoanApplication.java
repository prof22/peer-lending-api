package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Duration;
import java.util.Objects;

@Entity
public final class LoanApplication {

    @Id
    private long id;
    private  int amount;
    @ManyToOne
    private  User borrower;
    private  int repaymentTermInDays;
    private  double interestRate;

    public LoanApplication() {
    }

    public LoanApplication(int amount, User borrower, int repaymentTermInDays, double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentTermInDays = repaymentTermInDays;
        this.interestRate = interestRate;
    }

    public int getAmount() {
        return amount;
    }

    public User getBorrower() {
        return borrower;
    }


    public int getRepaymentTermInDays() {
        return repaymentTermInDays;
    }

    public double getInterestRate() {
        return interestRate;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplication that = (LoanApplication) o;
        return amount == that.amount && Double.compare(interestRate, that.interestRate) == 0 && Objects.equals(borrower, that.borrower) && Objects.equals(repaymentTermInDays, that.repaymentTermInDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrower, repaymentTermInDays, interestRate);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentTermInDays +
                ", interestRate=" + interestRate +
                '}';
    }
}
