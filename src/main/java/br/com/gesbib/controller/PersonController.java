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

import br.com.gesbib.dto.PersonDTO;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@PostMapping
	public PersonDTO create(@RequestBody PersonDTO person) {
		return null;
	}
	
	@GetMapping
	public Page<PersonDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
	{
		return null;
	}
	
	@PutMapping
	public PersonDTO update(@RequestBody PersonDTO person) {
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	public PersonDTO delete(@PathVariable(value="id") Long id) {
		return null;
	}
}
