package br.com.gesbib.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gesbib.dto.LoanDTO;
import br.com.gesbib.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/loan/v1")
@Tag(name = "Loan", description = "Endpoints for managing loans")
public class LoanController {
	
	private final LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@Operation(summary = "create a loan", description = "create a loan")
	@PostMapping
	public LoanDTO create(@RequestBody LoanDTO loanDTO) {
		return loanService.create(loanDTO);
	}
	
	@Operation(summary = "partialUpdate a loan", description = "partialUpdate a loan(only return Date and Status)")
	@PatchMapping("/{id}")
	public LoanDTO update(@PathVariable(value="id") Long id, @RequestBody LoanDTO loanDTO) {
		loanDTO.setId(id);
		return loanService.partialUpdate(loanDTO);
	}
	

}
