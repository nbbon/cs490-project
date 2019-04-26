package mum.pmp.mstore.repository.security;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Profile;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	//public List<Person> findByEmail(String email);
	
	public Profile findByEmail(String email);
	
}
