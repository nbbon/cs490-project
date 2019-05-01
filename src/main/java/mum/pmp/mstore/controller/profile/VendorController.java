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
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.VendorValidator;

@Controller
public class VendorController {

	@Autowired
	private  VendorValidator validator;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private Listener sessionListener;
	
	@GetMapping("/vendor/signup")
	public String signupPage(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "/profile/vendor_signup";
	}
	
	@PostMapping("/vendor/signup")
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
				bindingResult.rejectValue("email", "There is already an account registered with that email.");
				url =  "/profile/vendor_signup";
			}
		}
		return url;
	}
	
	@GetMapping("/vendor/update")
	public String updatePage(Model model) {
		System.out.println("in update : " + sessionListener.getUser().getEmail());
		Vendor vendorProfile = (Vendor) profileService.findByEmail(sessionListener.getUser().getEmail());
		System.out.println(">>" + vendorProfile);
		model.addAttribute("vendor" , vendorProfile);
		return "/profile/vendor_profile";
	}
	
	@PostMapping("/vendor/update")
	public String update(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		
		//validate the vendor details.
		validator.validate(vendor, bindingResult);
		
		// get a proxy object first to prevent duplicate entry 
		Profile person = profileService.findByEmail(vendor.getEmail());
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
			profileService.saveProfile(vendorToUpdate);
		}
		return "redirect:/vendor/update";
	}
	
	@PostMapping("/vendor/disable")
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
