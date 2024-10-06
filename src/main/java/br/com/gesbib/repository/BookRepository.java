package br.com.gesbib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gesbib.domain.Book;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByCategoryContains(String category);
}
