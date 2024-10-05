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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.gesbib.domain.Book;
import br.com.gesbib.dto.BookDTO;
import br.com.gesbib.exceptions.RequiredObjectIsNullException;
import br.com.gesbib.mocks.BookMocks;
import br.com.gesbib.repository.BookRepository;
import br.com.gesbib.service.BookService;

public class BookServiceTest {
	BookMocks input;
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	BookRepository bookRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new BookMocks();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Book book = input.mockEntity(1);
		
		BookDTO dto = input.mockDTO(1);
		dto.setId(1L);
		
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		
		BookDTO result = bookService.create(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals("title1", result.getTitle());
		assertEquals("author1", result.getAuthor());
		assertEquals("1111111111", result.getIsbn());
		assertEquals("category1", result.getCategory());
		assertEquals(LocalDate.now(), result.getPublishDate());
	}
	
	@Test
	void createWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			bookService.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testFindAll() {
		Page<Book> book = input.mockEntityList();
		
		Pageable pageable = PageRequest.of(0, 9);
		
		when(bookRepository.findAll(pageable)).thenReturn(book);
		
		var books = bookService.findAll(pageable);
		assertEquals(9, books.getContent().size());
		
		var bookOne = books.getContent().get(1);
		assertNotNull(bookOne);
		assertNotNull(bookOne.getId());
		assertEquals("title1", bookOne.getTitle());
		assertEquals("author1", bookOne.getAuthor());
		assertEquals("1111111111", bookOne.getIsbn());
		assertEquals("category1", bookOne.getCategory());
		assertEquals(LocalDate.now(), bookOne.getPublishDate());

		var bookFour = books.getContent().get(4);
		assertNotNull(bookFour);
		assertEquals("title4", bookFour.getTitle());
		assertEquals("author4", bookFour.getAuthor());
		assertEquals("4444444444", bookFour.getIsbn());
		assertEquals("category4", bookFour.getCategory());
		assertEquals(LocalDate.now(), bookFour.getPublishDate());
		
		var bookSeven = books.getContent().get(7);
		assertNotNull(bookSeven);
		assertEquals("title7", bookSeven.getTitle());
		assertEquals("author7", bookSeven.getAuthor());
		assertEquals("7777777777", bookSeven.getIsbn());
		assertEquals("category7", bookSeven.getCategory());
		assertEquals(LocalDate.now(), bookSeven.getPublishDate());
	}
	
	@Test
	void testUpdate() {
		Book book = input.mockEntity(1);
		Book persisted = book;
		persisted.setId(1L);
		
		BookDTO dto = input.mockDTO(1);
		dto.setId(1L);
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(book)).thenReturn(persisted);
		
		var result = bookService.update(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals("title1", result.getTitle());
		assertEquals("author1", result.getAuthor());
		assertEquals("1111111111", result.getIsbn());
		assertEquals("category1", result.getCategory());
		assertEquals(LocalDate.now(), result.getPublishDate());
	}
	
	@Test
	void updateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			bookService.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		BookDTO dto = input.mockDTO(1);
		dto.setId(1L);
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		
		bookService.delete(1L);
	}
}
