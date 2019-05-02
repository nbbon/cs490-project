package mum.pmp.mstore.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.config.security.Listener;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.ProductService;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.CustomerValidator;

@Controller
public class CustomerController {
	
	@Autowired
	private Listener sessionListener;
	
	@Autowired
	private CustomerValidator validator;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/customer/signup")
	public String signupPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "/profile/customer_signup";
	}

	@PostMapping("/customer/signup")
	public String signup(@ModelAttribute Customer customer, BindingResult bindingResult) {
		
		String url = "";
		//Validate the customer details.
		validator.validate(customer, bindingResult);
		
		if(bindingResult.hasErrors()) {
			url = "/profile/customer_signup";
		}
		else {
			if(profileService.signup(customer, User_Type.CUSTOMER)) {
				url = "redirect:/login";
			}
			else {
				bindingResult.rejectValue("email", "There is already an account registered with that email");
				url = "/profile/customer_signup";
			}
		}
		return url;
	}
	
	@GetMapping("/customer/update")
	public String updatePage(Model model) {
		Customer customerProfile = (Customer) profileService.findByEmail(sessionListener.getUser().getEmail());

		System.out.println("email in update sessionListener.getUser().getEmail(): "+sessionListener.getUser().getEmail());
		model.addAttribute("customer", customerProfile);
		return "/profile/customer_profile";
	}
	
	@PostMapping("/customer/update")
	public String update(@ModelAttribute Customer customer, BindingResult bindingResult) {
		
		//validate the admin details.
		validator.validate(customer, bindingResult);
		System.out.println("email in update: "+customer.getEmail());
		Profile person = profileService.findByEmail(customer.getEmail());
		Customer customerToUpdate;
		if(person instanceof Customer)
		{
			customerToUpdate = (Customer) person;
			customerToUpdate.setFirstName(customer.getFirstName());
			customerToUpdate.setLastName(customer.getLastName());
			customerToUpdate.setPassword(customer.getPassword());
			customerToUpdate.setPhone(customer.getPhone());
			customerToUpdate.setAddress(customer.getAddress());
			profileService.saveProfile(customerToUpdate);
		}
		return "redirect:/customer/update";
	}
	
	@PostMapping("/customer/disable")
	public String disableCustomer(@ModelAttribute("customer") Customer customer)
	{
		System.out.println(customer);
		System.out.println(customer.getEmail());
		
		Profile profile = profileService.findByEmail(customer.getEmail());
		System.out.println("My Profile: "+profile);
		byte status = 0;
		profile.setStatus(status);
		profileService.saveProfile(profile);
		return "redirect:/login";
	}
	
	@GetMapping("/catalogs")
	public String getAllCatalogs(Model model) {
		model.addAttribute("products", productService.getProducts());
		return "/catalog/catalog";
	}
}
