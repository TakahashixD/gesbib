package br.com.gesbib.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
import br.com.gesbib.dto.BookDTO;
import br.com.gesbib.mocks.RecommendationMocks;
import br.com.gesbib.repository.BookRepository;
import br.com.gesbib.repository.LoanRepository;
import br.com.gesbib.service.RecommendationService;

public class RecommendationServiceTest {
	RecommendationMocks input;
	@InjectMocks
	private RecommendationService recommendationService;
	
	@Mock
	BookRepository bookRepository;
	
	@Mock
	LoanRepository loanRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new RecommendationMocks();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void recommend() {
		Page<Loan> loans = input.mockEntityList();
		Page<Book> books = input.mockEntityListSame();

		Pageable page = PageRequest.of(0, 8);
		
		when(loanRepository.findByPersonId(1L, page)).thenReturn(loans);
		when(bookRepository.findByCategoryContains("Western")).thenReturn(books.getContent());

		Page<BookDTO> result = recommendationService.recommend(1L, page);
		assertNotNull(result);
		BookDTO bookZero = result.getContent().get(0);
		assertNotNull(bookZero);
		assertNotNull(bookZero.getId());
		assertEquals("title10", bookZero.getTitle());
		assertEquals("author10", bookZero.getAuthor());
		assertEquals("10101010101010101010", bookZero.getIsbn());
		assertEquals("Western", bookZero.getCategory());
		assertEquals(LocalDate.now(), bookZero.getPublishDate());
		
		BookDTO bookFour = result.getContent().get(4);
		assertNotNull(bookFour);
		assertNotNull(bookFour.getId());
		assertEquals("title14", bookFour.getTitle());
		assertEquals("author14", bookFour.getAuthor());
		assertEquals("14141414141414141414", bookFour.getIsbn());
		assertEquals("Western", bookFour.getCategory());
		assertEquals(LocalDate.now(), bookFour.getPublishDate());
	}
}
