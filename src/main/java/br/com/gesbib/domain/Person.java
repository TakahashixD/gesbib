package br.com.gesbib.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotNull
	@PastOrPresent
	@Column(name = "signup_date", nullable = false)
	private LocalDate signupDate;
	
	@NotNull
	@Column(name = "phone", nullable = false, unique = true)
	private String phone;
}
