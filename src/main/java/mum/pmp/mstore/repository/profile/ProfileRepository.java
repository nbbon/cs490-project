package mum.pmp.mstore.repository.profile;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Profile;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	public Profile findByEmail(String email);
	public Profile findByToken(String token);
	
}
