CREATE TABLE loan (
	id BIGSERIAL PRIMARY KEY,
	person_id BIGSERIAL NOT NULL,
	book_id BIGSERIAL NOT NULL,
	loan_date DATE NOT NULL,
	return_date DATE NOT NULL,
	status BOOLEAN NOT NULL,
    CONSTRAINT fk_person
   		FOREIGN KEY(person_id) 
      	REFERENCES person(id),
 	CONSTRAINT fk_book
   		FOREIGN KEY(book_id) 
      	REFERENCES book(id)
);