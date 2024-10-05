package br.com.gesbib.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO implements Serializable{
		
    private static final long serialVersionUID = 1L;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
	private Long personId; 
    private Long bookId;      
    private String author;  
    private String isbn;   
    private LocalDate loanDate;     
    private LocalDate returnDate;    
    private Boolean status; 
}
