package br.com.gesbib.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.gesbib.domain.Book;
import br.com.gesbib.domain.Loan;
import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.LoanDTO;

public class LoanMocks {
    public Loan mockEntity() {
        return mockEntity(0);
    }
    
    public LoanDTO mockDTO() {
        return mockDTO(0);
    }
    public Page<Loan> mockEntityList() {
        List<Loan> loans = new ArrayList<Loan>();
        for (int i = 0; i < 14; i++) {
        	loans.add(mockEntity(i));
        }
        Page<Loan> page = new PageImpl<>(loans);
        return page;
    }

    public Page<LoanDTO> mockDTOList() {
        List<LoanDTO> loans = new ArrayList<LoanDTO>();
        for (int i = 0; i < 14; i++) {
        	loans.add(mockDTO(i));
        }
        Page<LoanDTO> page = new PageImpl<>(loans);
        return page;
    }
    
    public Loan mockEntity(Integer number) {
    	BookMocks bookMock = new BookMocks();
    	Book book = bookMock.mockEntity(number);
    	PersonMocks personMock = new PersonMocks();
    	Person person = personMock.mockEntity(number);
    	
        Loan loan = new Loan();
        loan.setId(number.longValue());
        loan.setPerson(person);
        loan.setBook(book);
        loan.setAuthor("author"+number);
        loan.setIsbn(number.toString().repeat(9)+"-"+number);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(number%2 == 0);
        return loan;
    }

    public LoanDTO mockDTO(Integer number) {
        LoanDTO loan = new LoanDTO();
        loan.setId(number.longValue());
        loan.setPersonId(number.longValue());
        loan.setBookId(number.longValue());
        loan.setAuthor("author"+number);
        loan.setIsbn(number.toString().repeat(9)+"-"+number);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(number%2 == 0);
        return loan;
    }
}
