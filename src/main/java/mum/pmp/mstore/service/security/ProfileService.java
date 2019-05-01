package mum.pmp.mstore.service.security;

/*
 * Romie
 * */
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.repository.profile.AdminDAO;
import mum.pmp.mstore.repository.profile.ProfileRepository;
import mum.pmp.mstore.repository.profile.RoleRepository;
import mum.pmp.mstore.repository.profile.UserRepository;
import mum.pmp.mstore.service.email.EmailService;
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
	
	@Autowired
	private EmailService emailService;

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

	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

	public List<Admin> findAllAdmins() {
		return adminDAO.findAllAdmins();
	}

	public boolean signup(Profile profile, User_Type user_type) {

		// Check if user already exist.
		User existingUser = userRepository.findByUsername(profile.getEmail());
		System.out.println("existing user >>" + existingUser);
		if (existingUser == null) {
			User user = new User();
			// add user Role
			Role userRole = roleRepository.findByRole(user_type.toString());
			user.setUsername(profile.getEmail());
			user.addRole(userRole);
			if (userRole.getRole().equals("ADMIN")) {
				// Disable admin until Super admin approve the user.
				user.setEnabled(false);
				profile.setEnable(false);
			} else {
				user.setEnabled(true);
				profile.setEnable(true);
			}
			// encrypt the password with bCrypt
			user.setPassword(passwordEncoder.encode(profile.getPassword()));

			// save the user
			profile = saveProfile(profile);
			userRepository.save(user);
			return true;
		} else
			return false;

	}

	public Profile findProfileByToken(String token) {
		return profileRepository.findByToken(token);
	}

	public void approveAdmin(String adminEmail, String action) throws AddressException, MessagingException {
		
		Profile adminProfile = findByEmail(adminEmail);
		User user = userRepository.findByUsername(adminEmail);
		
		if(action.equals("Approve")) {
			adminProfile.setEnable(true);
			user.setEnabled(true);
		}else if(action.equals("Reject"))
		{
			adminProfile.setEnable(false);
			user.setEnabled(false);
		}
		userRepository.save(user);
		saveProfile(adminProfile);
		
		// send acknowledge email to admin
		
		emailService.sendEmail(adminEmail);
		
	}

	public boolean updateVendor(Vendor vendor) {
		Profile person = findByEmail(vendor.getEmail());
		Vendor vendorToUpdate;

		System.out.println("vendorToupdate" + person);
		if (person instanceof Vendor) {
			vendorToUpdate = (Vendor) person;
			vendorToUpdate.setVendorName(vendor.getVendorName());
			vendorToUpdate.setVendorNumber(vendor.getVendorNumber());
			vendorToUpdate.setRegId(vendor.getRegId());
			vendorToUpdate.setPassword(passwordEncoder.encode(vendor.getPassword()));
			vendorToUpdate.setPhone(vendor.getPhone());
			vendorToUpdate.setContactPerson(vendor.getContactPerson());

			User user = userRepository.findByUsername(vendor.getEmail());
			user.setPassword(passwordEncoder.encode(vendor.getPassword()));
			userRepository.save(user);
			saveProfile(vendorToUpdate);

			return true;
		}

		return false;
	}
}
