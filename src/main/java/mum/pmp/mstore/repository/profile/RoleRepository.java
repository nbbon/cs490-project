/*
 * Author: Yee Mon Zaw
 * Date: 26-May-2019
 * Class Name: RoleRepository
 * Package: mum.pmp.mstore.repository.profile
 * Description:  User Role Repository 
 * 
 */

package mum.pmp.mstore.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
