package br.com.gesbib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gesbib.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
