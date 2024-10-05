package br.com.gesbib.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gesbib.domain.Loan;

import br.com.gesbib.dto.LoanDTO;

import br.com.gesbib.exceptions.RequiredObjectIsNullException;
import br.com.gesbib.mocks.LoanMocks;
import br.com.gesbib.repository.BookRepository;
import br.com.gesbib.repository.LoanRepository;
import br.com.gesbib.repository.PersonRepository;
import br.com.gesbib.service.LoanService;

public class LoanServiceTest {
	
	LoanMocks input;
	
	@InjectMocks
	private LoanService loanService;
	
	@Mock
	LoanRepository loanRepository;
	
	@Mock
	BookRepository bookRepository;
	
	@Mock
	PersonRepository personRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new LoanMocks();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Loan loan = input.mockEntity(1);
		
		LoanDTO dto = input.mockDTO(1);
		
		when(bookRepository.findById((1L))).thenReturn(Optional.of(loan.getBook()));
		when(personRepository.findById((1L))).thenReturn(Optional.of(loan.getPerson()));
		when(loanRepository.save(any(Loan.class))).thenReturn(loan);
		
		LoanDTO result = loanService.create(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getBookId());
		assertNotNull(result.getPersonId());
		assertEquals("author1", result.getAuthor());
		assertEquals("111111111-1", result.getIsbn());
		assertEquals(LocalDate.now(), result.getLoanDate());
		assertEquals(LocalDate.now(), result.getReturnDate());
		assertEquals(false, result.getStatus());
	}
	
	@Test
	void createWithNullLoan() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			loanService.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void createWithNullPerson() {
		LoanDTO dto = input.mockDTO(1);
		dto.setPersonId(null);
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			loanService.create(dto);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void createWithNullBook() {
		LoanDTO dto = input.mockDTO(1);
		dto.setBookId(null);
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			loanService.create(dto);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testPartialUpdate() {
		Loan loan = input.mockEntity(1);
		Loan persisted = loan;
		persisted.setStatus(true);
		
		LoanDTO dto = input.mockDTO(1);
		dto.setStatus(true);
		dto.setReturnDate(LocalDate.of(2024, 11, 5));
		
		when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
		when(loanRepository.save(loan)).thenReturn(persisted);
		
		var result = loanService.partialUpdate(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getBookId());
		assertNotNull(result.getPersonId());
		assertEquals("author1", result.getAuthor());
		assertEquals("111111111-1", result.getIsbn());
		assertEquals(LocalDate.now(), result.getLoanDate());
		assertEquals(LocalDate.of(2024, 11, 5), result.getReturnDate());
		assertEquals(true, result.getStatus());
	}
	
	@Test
	void partialUpdateWithNullLoan() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			loanService.partialUpdate(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
}
