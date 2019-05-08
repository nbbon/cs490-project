package mum.pmp.mstore.mockdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.security.IProfileService;

@Component
public class ProfileServiceMockData //implements IProfileService
{

	//List<String> tokens = new ArrayList<>();
	List<Profile> profiles = new ArrayList<>();
	
	//@Override
	public Profile findProfileByToken(String token) {
		// TODO Auto-generated method stub
		//tokens.add("5040");
		Profile profile = new Profile();
		profile.setToken("5040");
		if(profile.getToken() == token)
			return profile;
		
		return null;
	}

	//@Override
	public Profile saveProfile(Profile person) {
		// TODO Auto-generated method stub
		profiles.add(person);
		return person;
	}

}
