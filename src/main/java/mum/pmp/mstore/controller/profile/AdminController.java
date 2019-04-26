package mum.pmp.mstore.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;

@Controller
public class AdminController {

	@Autowired
	private ProfileService personService;
	
	@GetMapping("/admin/signup")
	public String adminSignupPage(Model model) {
		model.addAttribute("admin", new Admin());
		return "/secure/admin_signup";
	}
	
	@PostMapping("/admin/signup")
	public String adminSignup(@ModelAttribute Admin admin) {
		personService.signup(admin, User_Type.ADMIN);
		return "redirect:/login";
	}
}
