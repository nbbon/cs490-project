package mum.pmp.mstore.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.service.security.PersonService;
import mum.pmp.mstore.utilities.User_Type;

@Controller
public class CustomerController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "/secure/signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute Customer customer) {
		personService.signup(customer, User_Type.CUSTOMER);
		return "redirect:/login";
	}
}
