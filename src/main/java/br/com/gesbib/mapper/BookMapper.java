package br.com.gesbib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.gesbib.domain.Book;
import br.com.gesbib.dto.BookDTO;

@Mapper
public interface BookMapper {
	BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );
	
    BookDTO bookToBookDTO(Book book);
    
    Book bookDTOToBook(BookDTO bookDTO);
}
