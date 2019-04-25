package mum.pmp.mstore.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.ProductService;
import mum.pmp.mstore.service.security.CustomUserDetailsService;
import mum.pmp.mstore.service.security.PersonService;
import mum.pmp.mstore.utilities.User_Type;

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
		model.addAttribute("customer", new Customer());
		return "/security/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute Customer customer) {
		personService.signup(customer, User_Type.CUSTOMER);
		return "redirect:/login";
	}
	
	@GetMapping("/admin/signup")
	public String adminSignupPage(Model model) {
		model.addAttribute("admin", new Admin());
		return "/security/admin_signup";
	}
	
	@PostMapping("/admin/signup")
	public String adminSignup(@ModelAttribute Admin admin) {
		personService.signup(admin, User_Type.ADMIN);
		return "redirect:/login";
	}
	
	@GetMapping("/vendor/signup")
	public String vendorSignupPage(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "/security/vendor_signup";
	}
	
	@PostMapping("/vendor/signup")
	public String vendorSignup(@ModelAttribute Vendor vendor) {
		personService.signup(vendor, User_Type.VENDOR);
		return "redirect:/login";
	}

}
