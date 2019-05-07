package mum.pmp.mstore.controller.profile;

import java.lang.annotation.Annotation;

import javax.validation.Payload;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.config.security.Listener;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.VendorValidator;

@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private  VendorValidator validator;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private Listener sessionListener;
	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("creditCard", new CreditCard());
		System.out.println("In signup credit card");
		return "/profile/vendor_signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		String url = "";
		//validate the vendor  details
			validator.validate(vendor, bindingResult);
			
			if(bindingResult.hasErrors()) {
				url =  "/profile/vendor_signup";
			}else {
				if(profileService.signup(vendor, User_Type.VENDOR)) {
					url = "redirect:/login";
				}
				else {
					bindingResult.rejectValue("email", "vendor.email.exist");
					url =  "/profile/vendor_signup";
				}
			}
		return url;
	}
	
	@GetMapping("/update")
	public String updatePage(Model model) {
		System.out.println("in update : " + sessionListener.getUser().getEmail());
		Vendor vendorProfile = (Vendor) profileService.findByEmail(sessionListener.getUser().getEmail());
		System.out.println(">>" + vendorProfile);
		model.addAttribute("vendor" , vendorProfile);
		return "/profile/vendor_profile";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		validator.validate(vendor, bindingResult);
		boolean status = profileService.updateVendor(vendor);
		if(status)
			return "/secure/login";
		else
			return "redirect:/vendor/update";
	}
	
	@PostMapping("/disable")
	public String disableVendor(@ModelAttribute("vendor") Vendor vendor)
	{
		System.out.println(vendor);
		System.out.println(vendor.getEmail());
		
		Profile profile = profileService.findByEmail(vendor.getEmail());
		System.out.println("My Profile: "+profile);
		byte status = 0;
		profile.setStatus(status);
		profileService.saveProfile(profile);
		return "redirect:/login";
	}
}
