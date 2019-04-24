package mum.pmp.mstore.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.pmp.mstore.model.Person;
import mum.pmp.mstore.service.ProductService;
import mum.pmp.mstore.service.security.CustomUserDetailsService;
import mum.pmp.mstore.service.security.PersonService;

@Controller
public class HomeController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private CustomUserDetailsService UserService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({"/"})
	public String homePage(Model model) {
		model.addAttribute("products", productService.getProducts());
		return "index";
	}

	@GetMapping({"/login"})
	public String securePage() {
		return "/security/login";
	}
	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("person", new Person());
		return "/security/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute Person person) {
		personService.signup(person);
		return "redirect:/login";
	}
	

}
