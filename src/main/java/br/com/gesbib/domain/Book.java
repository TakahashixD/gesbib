package br.com.gesbib.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Column(name = "publish_date", nullable = false)
	private LocalDate publishDate;
	
	@Column(name = "category", nullable = false)
	private String category;
}
