/*
 * Author: Yee Mon Zaw
 * Date: 02-May-2019
 * Class Name: ProfileRepository
 * Package: mum.pmp.mstore.repository.profile
 * Description: Profile repository
 * 
 */

package mum.pmp.mstore.repository.profile;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Profile;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	public Profile findByEmail(String email);
	public Profile findByToken(String token);
	
}
