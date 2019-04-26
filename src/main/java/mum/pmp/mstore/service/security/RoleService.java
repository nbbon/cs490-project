package mum.pmp.mstore.service.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.repository.profile.RoleRepository;

@Service
@Transactional
public class RoleService {

	private RoleRepository roleRepository;
	
	public List<Role> getAll(){
		return roleRepository.findAll();
	}
	
	public Role getRole(String role) {
		return roleRepository.findByRole(role);
	}
}
