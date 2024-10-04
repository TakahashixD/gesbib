package br.com.gesbib.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class LoanDTO implements Serializable{
		
    private static final long serialVersionUID = 1L;
    
    private Long id;
	private Long personId; 
    private Long bookId;      
    private String author;  
    private String isbn;   
    private LocalDate loanDate;     
    private LocalDate returnDate;    
    private Boolean status; 
}
