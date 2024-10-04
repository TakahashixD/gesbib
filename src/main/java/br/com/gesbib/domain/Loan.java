package br.com.gesbib.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "person_id")
	private Person personId;

    @ManyToOne
    @JoinColumn(name = "book_id")
	private Book bookId;
    
	@Column(name = "author", nullable = false)
	private String author;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Column(name = "loan_date", nullable = false)
	private LocalDate loanDate;
	
	@Column(name = "return_date", nullable = false)
	private LocalDate returnDate;
	
	@Column(name = "status", nullable = false)
	private Boolean status;
	
	
}
