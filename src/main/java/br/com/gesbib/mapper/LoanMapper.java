package br.com.gesbib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.gesbib.domain.Loan;
import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.LoanDTO;

@Mapper(componentModel = "spring", uses = { Person.class })
public interface LoanMapper{
	
	LoanMapper INSTANCE = Mappers.getMapper( LoanMapper.class );
	
	@Mapping(source = "person.id", target = "personId")
	@Mapping(source = "book.id", target = "bookId")
    LoanDTO loanToloanDTO(Loan loan);
	
	@Mapping(source = "personId", target = "person.id")
	@Mapping(source = "bookId", target = "book.id")
	Loan loanDTOToloan(LoanDTO loanDTO);
}
