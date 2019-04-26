package mum.pmp.mstore.controller.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.repository.security.RoleRepository;

@Controller
public class HomeController {

	@Autowired
	private RoleRepository roleService;

	@GetMapping({ "/" })
	public String homePage(Model model) {
		// Retrieve authenticated user details
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		model.addAttribute("userdetails", user);

		return "index";
	}

	@GetMapping({ "/login" })
	public String securePage() {
		return "/secure/login";
	}

	@ModelAttribute("roles")
	public List<Role> getRoles() {
		return roleService.findAll();
	}
}
