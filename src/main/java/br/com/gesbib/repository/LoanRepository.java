package br.com.gesbib.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gesbib.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	Page<Loan> findByPersonId(Long id, Pageable page);
}
