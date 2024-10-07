package br.com.gesbib.service;

import java.util.List;
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
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
	
	private Logger logger = Logger.getLogger(BookService.class.getName());
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	
	public BookDTO create(BookDTO bookDTO){
		if(bookDTO == null) throw new RequiredObjectIsNullException();
		logger.info("create book");
		Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
		return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(book));
	}
	
	public List<BookDTO> findAll(){
		logger.info("Finding all book");
		return BookMapper.INSTANCE.bookToBookDTO(bookRepository.findAll());
	}
	
	public Page<BookDTO> findAll(Pageable page){
		logger.info("Finding all book");
		return BookMapper.INSTANCE.bookToBookDTO(bookRepository.findAll(page));
	}
	
	public BookDTO update(BookDTO bookDTO){
		if(bookDTO == null) throw new RequiredObjectIsNullException();
		logger.info("update one book");
		Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		book.setPublishDate(bookDTO.getPublishDate());
		book.setCategory(bookDTO.getCategory());
		return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(book));
	}
	
	public void delete(Long id) {
		logger.info("delete one book");
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		bookRepository.delete(book);
	}
}
