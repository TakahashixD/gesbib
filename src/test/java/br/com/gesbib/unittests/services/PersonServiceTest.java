package br.com.gesbib.unittests.services;

import static org.junit.jupiter.api.Assertions.*;
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

import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.PersonDTO;
import br.com.gesbib.exceptions.RequiredObjectIsNullException;
import br.com.gesbib.mocks.PersonMocks;
import br.com.gesbib.repository.PersonRepository;
import br.com.gesbib.service.PersonService;

public class PersonServiceTest {
	
	PersonMocks input;
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	PersonRepository personRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new PersonMocks();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Person person = input.mockEntity(1);
		
		PersonDTO dto = input.mockDTO(1);
		dto.setId(1L);
		
		when(personRepository.save(any(Person.class))).thenReturn(person);
		
		PersonDTO result = personService.create(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals("teste+1@gmail.com", result.getEmail());
		assertEquals("teste1", result.getName());
		assertEquals("111111111", result.getPhone());
		assertEquals(LocalDate.now(), result.getSignupDate());

	}
	
	@Test
	void createWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personService.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testFindAll() {
		Page<Person> person = input.mockEntityList();
		
		Pageable pageable = PageRequest.of(0, 14);
		
		when(personRepository.findAll(pageable)).thenReturn(person);
		
		var people = personService.findAll(pageable);
		assertEquals(14, people.getContent().size());
		
		var personOne = people.getContent().get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getId());
		assertEquals("teste+1@gmail.com", personOne.getEmail());
		assertEquals("teste1", personOne.getName());
		assertEquals("111111111", personOne.getPhone());
		assertEquals(LocalDate.now(), personOne.getSignupDate());

		var personFour = people.getContent().get(4);
		assertNotNull(personFour);
		assertNotNull(personFour.getId());
		assertEquals("teste+4@gmail.com", personFour.getEmail());
		assertEquals("teste4", personFour.getName());
		assertEquals("444444444", personFour.getPhone());
		assertEquals(LocalDate.now(), personFour.getSignupDate());
		
		var personSeven = people.getContent().get(7);
		assertNotNull(personSeven);
		assertNotNull(personSeven.getId());
		assertEquals("teste+7@gmail.com", personSeven.getEmail());
		assertEquals("teste7", personSeven.getName());
		assertEquals("777777777", personSeven.getPhone());
		assertEquals(LocalDate.now(), personSeven.getSignupDate());
	}
	
	@Test
	void testUpdate() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		persisted.setId(1L);
		
		PersonDTO dto = input.mockDTO(1);
		dto.setId(1L);
		when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		when(personRepository.save(person)).thenReturn(persisted);
		
		var result = personService.update(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals("teste+1@gmail.com", result.getEmail());
		assertEquals("teste1", result.getName());
		assertEquals("111111111", result.getPhone());
		assertEquals(LocalDate.now(), result.getSignupDate());

	}
	
	@Test
	void updateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personService.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Person person = input.mockEntity(1);
		person.setId(1L);
		
		PersonDTO dto = input.mockDTO(1);
		dto.setId(1L);
		when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		
		personService.delete(1L);
	}
}
