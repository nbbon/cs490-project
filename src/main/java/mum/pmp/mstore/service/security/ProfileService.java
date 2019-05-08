package mum.pmp.mstore.service.security;

/*
 * Romie
 * */
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.repository.profile.ApprovalDAO;
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
	private ApprovalDAO adminDAO;
	
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

	public List<Admin> findNewAdmins() {
		return adminDAO.findNewAdmins();
	}
	
	public List<Vendor> findNewVendors() {
		return adminDAO.findNewVendors();
	}

	public boolean signup(Profile profile, User_Type user_type) {

		// Check if user already exist.
		User existingUser = userRepository.findByUsername(profile.getEmail());
		System.out.println("existing user >>" + existingUser);
		
		if (existingUser == null) {
			User user = new User();
			// add user Role
			System.out.println("user_type:"+user_type.toString());
			Role userRole = roleRepository.findByRole(user_type.toString());
			user.setUsername(profile.getEmail());
			user.addRole(userRole);
			System.out.println("Check userRole:"+userRole);
			if (userRole.getRole().equals("ADMIN")) { 
				user.setEnabled(false);
				profile.setEnable(false);
			} else if(userRole.getRole().equals("VENDOR")) {
				user.setEnabled(false);
				profile.setEnable(false);
			}
			else {
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

	//Super admin to approve admin user.
	public void approveAdmin(String adminEmail, String action) throws AddressException, MessagingException {
		
		Profile adminProfile = findByEmail(adminEmail);
		User user = userRepository.findByUsername(adminEmail);
		
		System.out.println(adminEmail);
		System.out.println(">>>>" + adminProfile );
		System.out.println(">>>>" + user);
		
		if(action.equals("Approve")) {
			adminProfile.setEnable(true);
			user.setEnabled(true);
			
			// send acknowledge email to admin
			String subject = "M-Store admin Registration confirmation";
			String body = "Your admin account " + adminEmail + " has been successfully approved. You may login to system using your email and password.";
			emailService.sendEmail(adminEmail, subject, body);
			
		}else if(action.equals("Reject"))
		{
			adminProfile.setEnable(false);
			user.setEnabled(false);
		}
		userRepository.save(user);
		saveProfile(adminProfile);
		
	}
	
	//Admin to approve vendor user.
	public void approveVendor(String adminEmail, String action) throws AddressException, MessagingException {
		
		Profile adminProfile = findByEmail(adminEmail);
		User user = userRepository.findByUsername(adminEmail);
		
		System.out.println("....approveVendor.....>>>" + adminEmail + ", " + action);
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
		String subject = "M-Store admin Registration confirmation";
		String body = "Your admin account " + adminEmail + " has been successfully approved. You may login to system using your email and password.";
		emailService.sendEmail(adminEmail, subject, body);
		
		System.out.println("Approved successfully.");
		
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
	
	public boolean updateAdmin(Admin admin) {
		Profile person = findByEmail(admin.getEmail());
		Admin adminToUpdate;

		System.out.println("adminToupdate" + person);
		if (person instanceof Admin) {
			adminToUpdate = (Admin) person;
			adminToUpdate.setFirstName(admin.getFirstName());
			adminToUpdate.setLastName(admin.getLastName());
			adminToUpdate.setPassword(admin.getPassword());
			adminToUpdate.setPassword(passwordEncoder.encode(admin.getPassword()));
			adminToUpdate.setPhone(admin.getPhone());

			User user = userRepository.findByUsername(admin.getEmail());
			user.setPassword(passwordEncoder.encode(admin.getPassword()));
			userRepository.save(user);
			saveProfile(adminToUpdate);

			return true;
		}

		return false;
	}
	
	public boolean updateCustomer(Customer customer) {
		Profile person = findByEmail(customer.getEmail());
		Customer customerToUpdate;

		System.out.println("customerToupdate" + person);
		if (person instanceof Customer) {
			customerToUpdate = (Customer) person;
			customerToUpdate.setFirstName(customer.getFirstName());
			customerToUpdate.setLastName(customer.getLastName());
			customerToUpdate.setPassword(passwordEncoder.encode(customer.getPassword()));
			customerToUpdate.setPhone(customer.getPhone());
			customerToUpdate.setAddress(customer.getAddress());
			
			User user = userRepository.findByUsername(customer.getEmail());
			user.setPassword(passwordEncoder.encode(customer.getPassword()));
			userRepository.save(user);
			saveProfile(customerToUpdate);
			return true;
		}

		return false;
	}
}
