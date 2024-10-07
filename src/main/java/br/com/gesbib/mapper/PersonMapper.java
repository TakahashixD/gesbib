package br.com.gesbib.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.PersonDTO;

@Mapper
public interface PersonMapper {
	PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );
	
    PersonDTO personToPersonDTO(Person person);
    
    Person personDTOToPerson(PersonDTO personDTO);
    
    List<PersonDTO> personToPersonDTO(List<Person> person);
    
    List<Person> personDTOToPerson(List<PersonDTO> personDTO);
    
    default Page<Person> personDTOToPerson(Page<PersonDTO> personDTO) {
    	return personDTO.map(x -> PersonMapper.INSTANCE.personDTOToPerson(x));
    }
    
    default Page<PersonDTO> personToPersonDTO(Page<Person> person) {
    	return person.map(x -> PersonMapper.INSTANCE.personToPersonDTO(x));
    }
}
