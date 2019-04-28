package mum.pmp.mstore.controller.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private Authentication auth;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/")
	public String home(Model model) {
		UserDetails user = secureOperations();
		model.addAttribute("userdetails", user);
		model.addAttribute("role", authorizedRole());
		return "index";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		System.out.println("Login???");
		return "/secure/login";
	}
	
	@RequestMapping("/profile")
	public String profilePage() {
		String url = "";
		UserDetails user = secureOperations();
		if(user != null) {
			String role = authorizedRole();
			System.out.println("role" + role);
			switch(role) {
				case "ROLE_ADMIN" : url = "redirect:/admin/update"; break;
				case "ROLE_CUSTOMER" : url = "redirect:/customer/update"; break;
				case "ROLE_VENDOR" : url = "redirect:/vendor/update"; break;
			}
		}
		return url;
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/secure/login?logout";
	}
	
	private UserDetails secureOperations() {
		auth = SecurityContextHolder
						.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		return user;
	}
	
	private String authorizedRole() {
		for(GrantedAuthority roles : auth.getAuthorities()) {
			String authorizedRole = roles.getAuthority();
			System.out.println(authorizedRole);
			return authorizedRole;
		}
		return null;
	}
}
