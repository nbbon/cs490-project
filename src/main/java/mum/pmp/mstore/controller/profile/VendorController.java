package mum.pmp.mstore.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.config.security.SessionListener;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.VendorValidator;

@Controller
public class VendorController {

	@Autowired
	private  VendorValidator validator;

	@Autowired
	private ProfileService registrationService;
	
	@Autowired
	private SessionListener sessionListener;
	
	@GetMapping("/vendor/signup")
	public String signupPage(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "/registration/vendor_signup";
	}
	
	@PostMapping("/vendor/signup")
	public String signup(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		
		String redirectURL = "";
		//validate the vendor  details
		validator.validate(vendor, bindingResult);
		
		if(bindingResult.hasErrors()) {
			redirectURL =  "/registration/vendor_signup";
		}else {
			if(registrationService.signup(vendor, User_Type.VENDOR)) {
				redirectURL = "redirect:/login";
			}
			else {
				bindingResult.rejectValue("email", "There is already an account registered with that email.");
				redirectURL =  "/registration/vendor_signup";
			}
		}
		return redirectURL;
	}
	
	@GetMapping("/vendor/update")
	public String updatePage(Model model) {
		System.out.println("in update : " + sessionListener.getUser().getEmail());
		Profile person = registrationService.findByEmail(sessionListener.getUser().getEmail());
		model.addAttribute("vendor" , person);
		return "/profile/vendor_profile";
	}
	
	@PostMapping("/vendor/update")
	public String update(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		
		// get a proxy object first to prevent duplicate entry 
		Profile person = registrationService.findByEmail(vendor.getEmail());
		Vendor vendorToUpdate;
		
		System.out.println("vendorToupdate" + person);
		if(person instanceof Vendor) {
			vendorToUpdate = (Vendor) person;
			vendorToUpdate.setVendorName(vendor.getVendorName());
			vendorToUpdate.setVendorNumber(vendor.getVendorNumber());
			vendorToUpdate.setRegId(vendor.getRegId());
			vendorToUpdate.setPassword(vendor.getPassword());
			vendorToUpdate.setPhone(vendor.getPhone());
			vendorToUpdate.setContactPerson(vendor.getContactPerson());
			registrationService.saveProfile(vendorToUpdate);
		}
		
		return "redirect:/vendor/update";
	}
}
