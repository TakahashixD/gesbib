package br.com.gesbib.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.PersonDTO;

public class PersonMocks {
    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonDTO mockDTO() {
        return mockDTO(0);
    }
    
    public Page<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        Page<Person> page = new PageImpl<>(persons);
        return page;
    }

    public Page<PersonDTO> mockDTOList() {
        List<PersonDTO> persons = new ArrayList<PersonDTO>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        Page<PersonDTO> page = new PageImpl<>(persons);
        return page;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setId(number.longValue());
        person.setEmail("teste+"+number+"@gmail.com");
        person.setName("teste"+number);
        person.setPhone(number.toString().repeat(9));
        person.setSignupDate(LocalDate.now());
        return person;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setId(number.longValue());
        person.setEmail("teste+"+number+"@gmail.com");
        person.setName("teste"+number);
        person.setPhone(number.toString().repeat(9));
        person.setSignupDate(LocalDate.now());
        return person;
    }
}
