package br.com.gesbib.service;

import java.util.Set;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gesbib.domain.Book;
import br.com.gesbib.dto.BookDTO;
import br.com.gesbib.mapper.BookMapper;
import br.com.gesbib.repository.BookRepository;
import br.com.gesbib.repository.LoanRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecommendationService {
	private Logger logger = Logger.getLogger(RecommendationService.class.getName());
	private final LoanRepository loanRepository;
	private final BookRepository bookRepository;
	
	public RecommendationService(LoanRepository loanRepository, BookRepository bookRepository) {
		this.loanRepository = loanRepository;
		this.bookRepository = bookRepository;
	}


	public Page<BookDTO> recommend(Long id, Pageable page){
		logger.info("recommend books");
		
		var loans = loanRepository.findByPersonId(id, page);
		
		Set<String> categories = loans.getContent().stream()
			.flatMap(loan -> Arrays.stream(loan.getBook().getCategory().split("\\|")))
			.collect(Collectors.toSet());
		
		Set<Long> loanedBooks = loans.getContent().stream()
			.map(x -> x.getBook().getId())
			.collect(Collectors.toSet());
		
		Set<Book> SetBooks = new LinkedHashSet<>();
		categories.forEach(category -> {
			SetBooks.addAll(bookRepository.findByCategoryContains(category));
		});
		
		var recommendedBooks = SetBooks.stream()
			.filter(book -> !loanedBooks.contains(book.getId()))
			.collect(Collectors.toList());

		List<BookDTO> recommendedBooksDTO = BookMapper.INSTANCE.bookToBookDTO(recommendedBooks);
		
		return new PageImpl<>(recommendedBooksDTO);

	}
}
