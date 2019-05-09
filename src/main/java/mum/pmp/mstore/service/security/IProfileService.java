package mum.pmp.mstore.service.security;

import mum.pmp.mstore.model.Profile;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: IProfileService
 * Module: Registration Module
 * Description: Interface for profile.
 * 
 */

public interface IProfileService {

	public Profile findProfileByToken(String token);
	public Profile saveProfile(Profile person);
}
