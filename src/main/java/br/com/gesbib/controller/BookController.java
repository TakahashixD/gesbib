package br.com.gesbib.controller;

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
import br.com.gesbib.service.BookService;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public BookDTO create(@RequestBody BookDTO book) {
		return bookService.create(book);
	}
	
	@GetMapping
	public Page<BookDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) 
	{
		Pageable pageable = PageRequest.of(page, size);
		return bookService.findAll(pageable);
	}
	
	@PutMapping
	public BookDTO update(@RequestBody BookDTO book) {
		return bookService.update(book);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
