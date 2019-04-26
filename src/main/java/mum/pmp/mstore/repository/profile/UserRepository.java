package mum.pmp.mstore.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String email);
}
