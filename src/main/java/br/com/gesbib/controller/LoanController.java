package br.com.gesbib.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Operation(summary = "Finds all loans", description = "Finds all loans")
	@GetMapping
	public Page<LoanDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
	{
		Pageable pageble = PageRequest.of(page, size, Sort.by("id").ascending());
		return loanService.findAll(pageble);
	}
	
	@Operation(summary = "partialUpdate a loan", description = "partialUpdate a loan(only return Date and Status)")
	@PatchMapping("/{id}")
	public LoanDTO update(@PathVariable(value="id") Long id, @RequestBody LoanDTO loanDTO) {
		loanDTO.setId(id);
		return loanService.partialUpdate(loanDTO);
	}
	

}
