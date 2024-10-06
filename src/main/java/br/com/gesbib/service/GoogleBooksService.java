package br.com.gesbib.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volume.VolumeInfo.IndustryIdentifiers;
import com.google.api.services.books.v1.model.Volumes;

import br.com.gesbib.dto.GoogleBooksDTO;
import br.com.gesbib.exceptions.ResourceNotFoundException;

@Service
public class GoogleBooksService {
	private final String KEY = "";

	public List<GoogleBooksDTO> searchBooks(String title) throws IOException, InterruptedException, GeneralSecurityException {
	    HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
	    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
		Books.Builder resourceManagerBuilder = new Books.Builder(transport, jsonFactory, null);
		Books books = resourceManagerBuilder.build();
		Volumes volumes = books.volumes().list(title).setLangRestrict("pt").setKey(KEY).setMaxResults(5L).execute();
		
		if(volumes.getItems() == null) throw new ResourceNotFoundException("No records found for this search!");
		
		List<GoogleBooksDTO> listGoogleBooksDTO = new ArrayList<>();
		volumes.getItems()
			.forEach(item ->{
					GoogleBooksDTO googleBooksDTO = new GoogleBooksDTO();
					String bookTitle = item.getVolumeInfo().getTitle();
					List<String> authors = item.getVolumeInfo().getAuthors();
					List<IndustryIdentifiers>isbn = item.getVolumeInfo().getIndustryIdentifiers();
					List<String> categories = item.getVolumeInfo().getCategories();
					String pubDate = item.getVolumeInfo().getPublishedDate();
					
					if(bookTitle == null || authors == null || isbn == null || categories == null || pubDate == null) {
						return;
					}
					
					googleBooksDTO.setTitle(bookTitle);
					googleBooksDTO.setAuthors(authors);
					googleBooksDTO.setIsbn(isbn);
					googleBooksDTO.setCategories(categories);
					googleBooksDTO.setPublishedDate(pubDate);
					
					listGoogleBooksDTO.add(googleBooksDTO);
				}
			);
		
		return listGoogleBooksDTO;
    }
}
