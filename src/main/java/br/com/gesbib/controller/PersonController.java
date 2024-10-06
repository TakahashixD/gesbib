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

import br.com.gesbib.dto.PersonDTO;
import br.com.gesbib.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Person", description = "Endpoints for managing users")
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@Operation(summary = "create a person", description = "create a person")
	@PostMapping
	public PersonDTO create(@RequestBody PersonDTO person) {
		return personService.create(person);
	}
	
	@Operation(summary = "Finds all people", description = "Finds all people")
	@GetMapping
	public Page<PersonDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
	{
		Pageable pageble = PageRequest.of(page, size);
		return personService.findAll(pageble);
	}
	
	@Operation(summary = "update a person", description = "update a person")
	@PutMapping(value = "/{id}")
	public PersonDTO update(@PathVariable(value="id") Long id, @RequestBody PersonDTO person) {
		person.setId(id);
		return personService.update(person);
	}
	
	@Operation(summary = "delete a person", description = "delete a person")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
