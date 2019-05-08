package mum.pmp.mstore.service.security;

import mum.pmp.mstore.model.Profile;


public interface IProfileService {

	public Profile findProfileByToken(String token);
	public Profile saveProfile(Profile person);
}
