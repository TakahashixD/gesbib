package br.com.gesbib.service;

import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.PersonDTO;
import br.com.gesbib.exceptions.RequiredObjectIsNullException;
import br.com.gesbib.mapper.PersonMapper;
import br.com.gesbib.repository.PersonRepository;
import jakarta.transaction.Transactional;
import br.com.gesbib.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	private final PersonRepository personRepository;
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	
	public PersonDTO create(PersonDTO personDTO){
		if(personDTO == null) throw new RequiredObjectIsNullException();
		logger.info("create person");
		Person person = PersonMapper.INSTANCE.personDTOToPerson(personDTO);
		return PersonMapper.INSTANCE.personToPersonDTO(personRepository.save(person));
	}
	
	public Page<PersonDTO> findAll(Pageable page){
		logger.info("Finding all person");
		return personRepository.findAll(page).map(x -> PersonMapper.INSTANCE.personToPersonDTO(x));
	}
	
	public PersonDTO update(PersonDTO personDTO){
		if(personDTO == null) throw new RequiredObjectIsNullException();
		logger.info("update one person");
		Person person = personRepository.findById(personDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return PersonMapper.INSTANCE.personToPersonDTO(personRepository.save(person));
	}
	
	public void delete(Long id) {
		logger.info("delete one person");
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		personRepository.delete(person);
	}
}
