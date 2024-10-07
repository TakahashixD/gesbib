package br.com.gesbib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import br.com.gesbib.domain.Book;
import br.com.gesbib.dto.BookDTO;
import java.util.List;

@Mapper
public interface BookMapper {
	BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );
	
    BookDTO bookToBookDTO(Book book);
    
    List<BookDTO> bookToBookDTO(List<Book> book);
    
    Book bookDTOToBook(BookDTO bookDTO);
    
    List<Book> bookDTOToBook(List<BookDTO> bookDTO);
    
    default Page<Book> bookDTOToBook(Page<BookDTO> bookDTO) {
    	return bookDTO.map(x -> BookMapper.INSTANCE.bookDTOToBook(x));
    }
    
    default Page<BookDTO> bookToBookDTO(Page<Book> book) {
    	return book.map(x -> BookMapper.INSTANCE.bookToBookDTO(x));
    }
}
