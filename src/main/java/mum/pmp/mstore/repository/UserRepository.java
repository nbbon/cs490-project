package mum.pmp.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

	 User findByUsername(String username);
}
