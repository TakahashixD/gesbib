package br.com.gesbib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import br.com.gesbib.domain.Loan;
import br.com.gesbib.dto.LoanDTO;

@Mapper
public interface LoanMapper{
	
	LoanMapper INSTANCE = Mappers.getMapper( LoanMapper.class );
	
	@Mapping(source = "person.id", target = "personId")
	@Mapping(source = "book.id", target = "bookId")
    LoanDTO loanToloanDTO(Loan loan);
	
	@Mapping(source = "personId", target = "person.id")
	@Mapping(source = "bookId", target = "book.id")
	Loan loanDTOToloan(LoanDTO loanDTO);
	
	@Mapping(source = "person.id", target = "personId")
	@Mapping(source = "book.id", target = "bookId")
    default Page<LoanDTO> loanToloanDTO(Page<Loan> loan){
		return loan.map(x -> LoanMapper.INSTANCE.loanToloanDTO(x));
	};
	
	@Mapping(source = "personId", target = "person.id")
	@Mapping(source = "bookId", target = "book.id")
	default Page<Loan> loanDTOToloan(Page<LoanDTO> loanDTO){
		return loanDTO.map(x -> LoanMapper.INSTANCE.loanDTOToloan(x));
	};
}
