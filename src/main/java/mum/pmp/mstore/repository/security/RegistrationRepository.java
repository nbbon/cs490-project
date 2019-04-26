package mum.pmp.mstore.repository.security;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Person;


@Repository
public interface RegistrationRepository extends JpaRepository<Person, Long> {

	//public List<Person> findByEmail(String email);
	
	public Person findByEmail(String email);
	
}
