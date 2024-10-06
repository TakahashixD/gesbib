package br.com.gesbib.dto;

import java.io.Serializable;
import java.util.List;

import com.google.api.services.books.v1.model.Volume.VolumeInfo.IndustryIdentifiers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleBooksDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private List<String> authors;
	private List<IndustryIdentifiers> isbn;
	private List<String> categories;
	private String publishedDate;

}
