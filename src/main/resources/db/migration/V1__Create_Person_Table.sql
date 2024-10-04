CREATE EXTENSION citext;
CREATE DOMAIN email_type AS citext
  CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

CREATE TABLE person (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	email email_type NOT NULL,
	signup_date DATE NOT NULL,
	phone VARCHAR(255) NOT NULL,
	UNIQUE(email, phone)
);