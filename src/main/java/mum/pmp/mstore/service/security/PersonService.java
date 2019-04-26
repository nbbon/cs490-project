package mum.pmp.mstore.service.security;

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
	public Person findPersonByToken(String token)
	{
		return personRepository.findByToken(token);
	}
	public void signup(Person person, User_Type user_type) {
		person.setEnable(true);;

		User user = new User();
		user.setEmail(person.getEmail());
		user.setEnabled(true);
		Role userRole = roleRepository.findByRole(user_type.toString());
		
		System.out.println("customerRole:" + userRole);
		user.addRole(userRole);
		//user.setPassword(person.getPassword());
		user.setPassword(passwordEncoder.encode(person.getPassword()));

		person = personRepository.save(person);
		userRepository.save(user);
	}
}
