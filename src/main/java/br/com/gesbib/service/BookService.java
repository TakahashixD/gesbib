package br.com.gesbib.service;

import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gesbib.domain.Book;
import br.com.gesbib.dto.BookDTO;
import br.com.gesbib.exceptions.RequiredObjectIsNullException;
import br.com.gesbib.exceptions.ResourceNotFoundException;
import br.com.gesbib.mapper.BookMapper;
import br.com.gesbib.repository.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	public BookDTO create(BookDTO bookDTO){
		if(bookDTO == null) throw new RequiredObjectIsNullException();
		logger.info("save book");
		Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
		return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(book));
	}
	
	public Page<BookDTO> findAll(Pageable page){
		logger.info("Finding all book");
		return bookRepository.findAll(page).map(x -> BookMapper.INSTANCE.bookToBookDTO(x));
	}
	
	public BookDTO update(BookDTO bookDTO){
		if(bookDTO == null) throw new RequiredObjectIsNullException();
		logger.info("update one book");
		Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(book));
	}
	
	public void delete(Long id) {
		logger.info("delete one book");
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		bookRepository.delete(book);
	}
}
