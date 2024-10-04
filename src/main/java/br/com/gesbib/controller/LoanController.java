package br.com.gesbib.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gesbib.dto.LoanDTO;

@RestController
@RequestMapping("/api/loan/v1")
public class LoanController {
	
	@PostMapping
	public LoanDTO create(@RequestBody LoanDTO loan) {
		return null;
	}
	
	@GetMapping
	public LoanDTO get() {
		return null;
	}
	
	@PutMapping
	public LoanDTO update(@RequestBody LoanDTO loan) {
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	public LoanDTO delete(@PathVariable(value="id") Long id) {
		return null;
	}
}
