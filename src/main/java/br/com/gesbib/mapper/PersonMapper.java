package br.com.gesbib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.PersonDTO;

@Mapper
public interface PersonMapper {
	PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );
    PersonDTO personToPersonDTO(Person person);
}
