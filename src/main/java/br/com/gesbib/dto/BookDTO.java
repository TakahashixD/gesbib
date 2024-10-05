package br.com.gesbib.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String author;
	private String isbn;
	private LocalDate publish_date;
	private String category;
}
