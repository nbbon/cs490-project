package mum.pmp.mstore.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.model.Password;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.profile.UserRepository;
import mum.pmp.mstore.service.security.IProfileService;
import mum.pmp.mstore.service.security.ProfileService;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: ResetPasswordService
 * Module: Reset Password
 * Description: The implementation of reset password  
 * 
 */

@Service
public class ResetPasswordService {

	@Autowired
	@Qualifier("profileService")
	IProfileService profileService;
	
	//@Resource
	//@Qualifier("profileServiceMockData")
	//IProfileService profileService;

	@Autowired
	UserRepository userRepository;
	
	//@Autowired
	//@Qualifier("userRepositoryMockData")
    //UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String resetPassword(Password password) {
		System.out.println("profileService: "+profileService);
		Profile profile = profileService.findProfileByToken(password.getToken());

		//System.out.println("profileServiceMockData: "+ profileServiceMockData);
		System.out.println("profileService: "+profileService);
		if (profile == null) {
			return "password/forgot_password";
		}

		profile.setPassword(passwordEncoder.encode(password.getPassword()));
		profile.setToken(null);
		profileService.saveProfile(profile);

		// set for user details.
		User user = userRepository.findByUsername(profile.getEmail());
		System.out.println("user >>" + user);
		user.setPassword(passwordEncoder.encode(password.getPassword()));
		userRepository.save(user);

		return "redirect:/login";
	}
}
