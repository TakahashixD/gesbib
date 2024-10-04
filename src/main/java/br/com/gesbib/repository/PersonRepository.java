package br.com.gesbib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gesbib.domain.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
