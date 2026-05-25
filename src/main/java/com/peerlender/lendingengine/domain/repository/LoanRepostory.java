package com.peerlender.lendingengine.domain.repository;

import com.peerlender.lendingengine.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepostory extends JpaRepository<Loan, Long> {
}
