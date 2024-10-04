package br.com.gesbib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.gesbib.domain.Loan;
import br.com.gesbib.dto.LoanDTO;



@Mapper
public interface LoanMapper {
	LoanMapper INSTANCE = Mappers.getMapper( LoanMapper.class );
    LoanDTO loanToloanDTO(Loan loan);
}
