package mum.pmp.mstore.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.PersonService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.VendorValidator;

@Controller
public class VendorController {

	@Autowired
	private  VendorValidator validator;

	@Autowired
	private PersonService personService;
	
	@GetMapping("/vendor/signup")
	public String vendorSignupPage(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "/registration/vendor_signup";
	}
	
	@PostMapping("/vendor/signup")
	public String vendorSignup(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		
		String redirectURL = "";
		//validate the vendor  details
		validator.validate(vendor, bindingResult);
		
		if(bindingResult.hasErrors()) {
			redirectURL =  "/registration/vendor_signup";
		}else {
			if(personService.signup(vendor, User_Type.VENDOR)) {
				redirectURL = "redirect:/login";
			}
			else {
				bindingResult.rejectValue("email", "There is already an account registered with that email.");
				redirectURL =  "/registration/vendor_signup";
			}
		}
		return redirectURL;
	}
}
