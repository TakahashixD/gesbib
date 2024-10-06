package br.com.gesbib.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.gesbib.domain.Book;
import br.com.gesbib.domain.Loan;
import br.com.gesbib.domain.Person;

public class RecommendationMocks {
	
	
    public Page<Loan> mockEntityList() {
    	PersonMocks personMock = new PersonMocks();
    	Person person = personMock.mockEntity(1);
        List<Loan> loans = new ArrayList<Loan>();
        for (int i = 0; i < 10; i++) {
        	loans.add(mockEntityLoan(i, person));
        }
        Page<Loan> page = new PageImpl<>(loans);
        return page;
    }
    
    public Page<Book> mockEntityListSame() {
    	BookMocks bookMock = new BookMocks();
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 20; i++) {
            books.add(bookMock.mockEntitySameCategory(i));
        }
        Page<Book> page = new PageImpl<>(books);
        return page;
    }
    
    public Loan mockEntityLoan(Integer number, Person person) {
    	BookMocks bookMock = new BookMocks();
    	Book book = bookMock.mockEntitySameCategory(number);
    	
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
}
