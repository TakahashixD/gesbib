package br.com.gesbib.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gesbib.dto.BookDTO;
import br.com.gesbib.dto.GoogleBooksDTO;
import br.com.gesbib.service.BookService;
import br.com.gesbib.service.GoogleBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for managing books")
public class BookController {
	
	private final BookService bookService;
	private final GoogleBooksService googleBooksService;
	
	public BookController(
			BookService bookService,
			GoogleBooksService googleBooksService
	) {
		this.bookService = bookService;
		this.googleBooksService = googleBooksService;
	}

	@Operation(summary = "create a book", description = "create a book")
	@PostMapping
	public BookDTO create(@RequestBody BookDTO book) {
		return bookService.create(book);
	}
	
	@Operation(summary = "find books", description = "find books")
	@GetMapping
	public Page<BookDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) 
	{
		Pageable pageable = PageRequest.of(page, size);
		return bookService.findAll(pageable);
	}
	
	@Operation(summary = "update a book", description = "update a book")
	@PutMapping(value = "/{id}")
	public BookDTO update(@PathVariable(value="id") Long id, @RequestBody BookDTO book) {
		book.setId(id);
		return bookService.update(book);
	}
	
	@Operation(summary = "delete a book", description = "delete a book")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "search books from Google Books API", description = "search books from Google Books API")
    @GetMapping("/search")
    public List<GoogleBooksDTO> searchBooks(@RequestParam String title) throws IOException, InterruptedException, GeneralSecurityException {
    	return googleBooksService.searchBooks(title);
    }
}
