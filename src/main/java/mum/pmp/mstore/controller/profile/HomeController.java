package mum.pmp.mstore.controller.profile;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails user = (UserDetails) auth.getPrincipal();
		model.addAttribute("userdetails", user);
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		System.out.println("Login???");
		return "/secure/login";
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage() {
		return "/secure/logout";
	}
}
