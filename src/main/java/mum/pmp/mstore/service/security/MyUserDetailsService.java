package mum.pmp.mstore.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.model.User;
import mum.pmp.mstore.model.UserPrincipal;
import mum.pmp.mstore.repository.profile.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Step - 3 
		// Get username from the repository
		User user = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("User 404");
		
		
		return new UserPrincipal(user);
	}

}
