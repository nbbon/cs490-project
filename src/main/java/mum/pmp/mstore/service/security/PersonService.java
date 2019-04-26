package mum.pmp.mstore.service.security;
/*
 * Romie
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.pmp.mstore.model.Person;
import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.security.PersonRepository;
import mum.pmp.mstore.repository.security.RoleRepository;
import mum.pmp.mstore.repository.security.UserRepository;
import mum.pmp.mstore.utilities.User_Type;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public Person findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(Long id) {
		return personRepository.findById(id).get();
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public boolean signup(Person person, User_Type user_type) {
		
		// Check if user already exist.
		User existingUser = userRepository.findByEmail(person.getEmail());
		System.out.println("existing user >>" + existingUser);
		if(existingUser == null) {
			//enable the user account.
			person.setEnable(true);;
	
			User user = new User();
			//set the email.
			user.setEmail(person.getEmail());
			user.setEnabled(true);
			
			//add user Role
			Role userRole = roleRepository.findByRole(user_type.toString());
			user.setRole(userRole);
			
			//encrypt the password with bCrypt
			user.setPassword(passwordEncoder.encode(person.getPassword()));

			//save the user
			person = personRepository.save(person);
			userRepository.save(user);
			return true;
		}
		else 
			return false;
		
	}
}
