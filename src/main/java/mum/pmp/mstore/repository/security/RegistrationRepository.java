package mum.pmp.mstore.repository.security;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Profile;


@Repository
public interface RegistrationRepository extends JpaRepository<Profile, Long> {

	//public List<Person> findByEmail(String email);
	
<<<<<<< HEAD:src/main/java/mum/pmp/mstore/repository/security/PersonRepository.java
	public Person findByEmail(String email);
	public Person findByToken(String token);
=======
	public Profile findByEmail(String email);
>>>>>>> upstream/master:src/main/java/mum/pmp/mstore/repository/security/RegistrationRepository.java
	
}
