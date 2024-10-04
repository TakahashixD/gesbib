package br.com.gesbib.controller;

import org.springframework.data.domain.Page;
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

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@PostMapping
	public BookDTO create(@RequestBody BookDTO book) {
		return null;
	}
	
	@GetMapping
	public Page<BookDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) 
	{
		return null;
	}
	
	@PutMapping
	public BookDTO update(@RequestBody BookDTO book) {
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	public BookDTO delete(@PathVariable(value="id") Long id) {
		return null;
	}
}
