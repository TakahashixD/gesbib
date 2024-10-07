package br.com.gesbib.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.gesbib.domain.Book;
import br.com.gesbib.dto.BookDTO;

public class BookMocks {
    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockDTO() {
        return mockDTO(0);
    }
    
    public Page<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 9; i++) {
            books.add(mockEntity(i));
        }
        Page<Book> page = new PageImpl<>(books);
        return page;
    }

    public Page<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<BookDTO>();
        for (int i = 0; i < 9; i++) {
            books.add(mockDTO(i));
        }
        Page<BookDTO> page = new PageImpl<>(books);
        return page;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("title"+number);
        book.setAuthor("author"+number);
        book.setCategory("category"+number);
        book.setIsbn(number.toString().repeat(10));
        book.setPublishDate(LocalDate.now());
        return book;
    }
    
    public Book mockEntitySameCategory(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("title"+number);
        book.setAuthor("author"+number);
        book.setCategory("Western");
        book.setIsbn(number.toString().repeat(10));
        book.setPublishDate(LocalDate.now());
        return book;
    }
    
    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setId(number.longValue());
        book.setTitle("title"+number);
        book.setAuthor("author"+number);
        book.setCategory("category"+number);
        book.setIsbn(number.toString().repeat(10));
        book.setPublishDate(LocalDate.now());
        return book;
    }
}
