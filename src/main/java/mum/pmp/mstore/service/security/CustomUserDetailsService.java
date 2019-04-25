package mum.pmp.mstore.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.adapters.UserAdapter;
import mum.pmp.mstore.model.Person;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.security.UserRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonService personService;

	public CustomUserDetailsService() {
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("In load user by name");
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User with %s doesn't exist!", email));
		}
		//List<Person> persons = personService.findByEmail(user.getEmail());
		Person person = personService.findByEmail(user.getEmail());
		return new UserAdapter(user, person);
		
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
