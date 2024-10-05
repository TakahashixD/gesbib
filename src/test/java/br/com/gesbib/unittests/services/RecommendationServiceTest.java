package br.com.gesbib.unittests.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.gesbib.domain.Book;
import br.com.gesbib.domain.Loan;
import br.com.gesbib.mocks.BookMocks;
import br.com.gesbib.mocks.LoanMocks;
import br.com.gesbib.repository.BookRepository;
import br.com.gesbib.repository.LoanRepository;
import br.com.gesbib.service.RecommendationService;

public class RecommendationServiceTest {
	
	LoanMocks inputLoan;
	BookMocks inputBook;
	@InjectMocks
	private RecommendationService recommendationService;
	
	@Mock
	BookRepository bookRepository;
	
	@Mock
	LoanRepository loanRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		inputLoan = new LoanMocks();
		inputBook = new BookMocks();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void recommend() {
		Pageable page = PageRequest.of(0, 2);
		Page<Loan> loans = inputLoan.mockEntityList();
		when(loanRepository.findByPersonId(1L, page)).thenReturn(loans);
		when(bookRepository.findByCategoryContaining(category)).thenReturn();
	}
}
