package br.com.gesbib.unittests.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volumes;

import br.com.gesbib.service.GoogleBooksService;

public class GoogleBooksServiceTest {
	
	private static final String KEY = null;
	
	@InjectMocks
	private GoogleBooksService googleBooksService;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void searchBooksTest() throws IOException {
		Books books = mock(Books.class, RETURNS_DEEP_STUBS);
		Volumes volumes = mock(Volumes.class);
		when(books.volumes().list(anyString()).setLangRestrict("pt").setKey(KEY).setMaxResults(5L).execute())
			.thenReturn(volumes);
		verify(books.volumes().list(anyString()).setLangRestrict("pt").setKey(KEY).setMaxResults(5L).execute());
		assertNotNull(volumes);
	}
}
