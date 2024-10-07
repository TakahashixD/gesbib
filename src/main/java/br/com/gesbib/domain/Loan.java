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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "loan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
	private Person person;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
	private Book book;
	
	@NotNull
    @PastOrPresent
	@Column(name = "loan_date", nullable = false)
	private LocalDate loanDate;
	
	@NotNull
	@Column(name = "return_date", nullable = false)
	private LocalDate returnDate;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private Boolean status;
	
	
}
