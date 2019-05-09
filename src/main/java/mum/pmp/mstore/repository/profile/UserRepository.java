/*
 * Author: Yee Mon Zaw
 * Date: 26-May-2019
 * Class Name: UserRepository
 * Package: mum.pmp.mstore.repository.profile
 * Description:  User Repository - use for login
 * 
 */

package mum.pmp.mstore.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
