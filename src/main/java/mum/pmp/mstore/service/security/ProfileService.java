package mum.pmp.mstore.service.security;
/*
 * Romie
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.security.ProfileRepository;
import mum.pmp.mstore.repository.security.RoleRepository;
import mum.pmp.mstore.repository.security.UserRepository;
import mum.pmp.mstore.utilities.User_Type;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Profile saveProfile(Profile person) {
		return profileRepository.save(person);
	}

	public Profile findByEmail(String email) {
		return profileRepository.findByEmail(email);
	}

	public Profile findById(Long id) {
		return profileRepository.findById(id).get();
	}
	
	public void remove(Profile person) {
		profileRepository.delete(person);
	}

	public List<Profile> findAll(){
		return profileRepository.findAll();
	}
	
	public boolean signup(Profile person, User_Type user_type) {
		
		// Check if user already exist.
		User existingUser = userRepository.findByUserId(person.getEmail());
		System.out.println("existing user >>" + existingUser);
		if(existingUser == null) {
			//enable the user account.
			person.setEnable(true);;
	
			User user = new User();
			//set the email.
			user.setUserId(person.getEmail());
			user.setEnabled(true);
			
			//add user Role
			Role userRole = roleRepository.findByRole(user_type.toString());
			user.setRole(userRole);
			
			//encrypt the password with bCrypt
			user.setPassword(passwordEncoder.encode(person.getPassword()));

			//save the user
			person = profileRepository.save(person);
			userRepository.save(user);
			return true;
		}
		else 
			return false;
		
	}
	
	public Profile findProfileByToken(String token) {
		return profileRepository.findByToken(token);
	}
	
}
