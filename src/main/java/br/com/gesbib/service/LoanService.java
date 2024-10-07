package br.com.gesbib.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gesbib.domain.Book;
import br.com.gesbib.domain.Loan;
import br.com.gesbib.domain.Person;
import br.com.gesbib.dto.LoanDTO;
import br.com.gesbib.exceptions.AlreadyActiveException;
import br.com.gesbib.exceptions.RequiredObjectIsNullException;
import br.com.gesbib.mapper.LoanMapper;
import br.com.gesbib.repository.BookRepository;
import br.com.gesbib.repository.LoanRepository;
import br.com.gesbib.repository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {
	
	private Logger logger = Logger.getLogger(LoanService.class.getName());
	private final LoanRepository loanRepository;
	private final PersonRepository personRepository;
	private final BookRepository bookRepository;
	
	public LoanService(LoanRepository loanRepository, PersonRepository personRepository,
			BookRepository bookRepository) {
		this.loanRepository = loanRepository;
		this.personRepository = personRepository;
		this.bookRepository = bookRepository;
	}
	
	public LoanDTO create(LoanDTO loanDTO) {
		if(loanDTO == null) throw new RequiredObjectIsNullException();
		
		logger.info("create loan");
		
		Optional<Book> book = bookRepository.findById(loanDTO.getBookId());
		Optional<Person> person = personRepository.findById(loanDTO.getPersonId());
		if(book.isEmpty() || person.isEmpty()) throw new RequiredObjectIsNullException();
		
		Optional<List<Loan>> listOfActive = loanRepository.findByStatusAndBookId(true, loanDTO.getBookId());
		if(listOfActive.isPresent() && !listOfActive.get().isEmpty()) throw new AlreadyActiveException("Book not avaliable!");
		
		Loan loan = LoanMapper.INSTANCE.loanDTOToloan(loanDTO);
		loan.setBook(book.get());
		loan.setPerson(person.get());
		
		return LoanMapper.INSTANCE.loanToloanDTO(loanRepository.save(loan));
	}
	
	public Page<LoanDTO> findAll(Pageable page){
		logger.info("Finding all loans");
		return LoanMapper.INSTANCE.loanToloanDTO(loanRepository.findAll(page));
	}
	
	public LoanDTO partialUpdate(LoanDTO loanDTO) {
		if(loanDTO == null) throw new RequiredObjectIsNullException();
		
		logger.info("partial update loan");
		
		Optional<Loan> loan = loanRepository.findById(loanDTO.getId()).map(entity -> {
            if (loanDTO.getStatus() != null) {
            	entity.setStatus(loanDTO.getStatus());
            }
            if (loanDTO.getReturnDate() != null) {
            	entity.setReturnDate(loanDTO.getReturnDate());
            }
            return entity;
		})
		.map(loanRepository::save);
		
		if(loan.isEmpty()) throw new RequiredObjectIsNullException();
		
		return LoanMapper.INSTANCE.loanToloanDTO(loan.get());
	}
}
