package mum.pmp.mstore.service.security;
/*
 * Romie
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.profile.AdminDAO;
import mum.pmp.mstore.repository.profile.ProfileRepository;
import mum.pmp.mstore.repository.profile.RoleRepository;
import mum.pmp.mstore.repository.profile.UserRepository;
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
	
	@Autowired
	private AdminDAO adminDAO;
	
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
	
	public List<Admin> findAllAdmins(){
		return adminDAO.findAllAdmins();
	}
	
	public boolean signup(Profile profile, User_Type user_type) {
		
		// Check if user already exist.
		User existingUser = userRepository.findByUsername(profile.getEmail());
		System.out.println("existing user >>" + existingUser);
		if(existingUser == null) {
			
			User user = new User();
			//add user Role
			Role userRole = roleRepository.findByRole(user_type.toString());
			//set the email.
			user.setUsername(profile.getEmail());
			//Add roles 
			user.addRole(userRole);
			System.out.println(userRole.getRole());
			if(userRole.getRole().equals("ADMIN")) {
				user.setEnabled(false);
				profile.setEnable(false);
			}
			else {
				user.setEnabled(true);
				profile.setEnable(true);
			}
			
			//encrypt the password with bCrypt
			user.setPassword(passwordEncoder.encode(profile.getPassword()));
			
			//save the user
			profile = profileRepository.save(profile);
			userRepository.save(user);
			return true;
		}
		else 
			return false;
		
	}
	
	public Profile findProfileByToken(String token) {
		return profileRepository.findByToken(token);
	}

	public void approveAdmin(String adminEmail) {
		Profile adminProfile = findByEmail(adminEmail);
		adminProfile.setEnable(true);
		
		User user = userRepository.findByUsername(adminEmail);
		user.setEnabled(true);
		
		userRepository.save(user);
		saveProfile(adminProfile);
	}
	
}
