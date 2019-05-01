package mum.pmp.mstore.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.config.security.Listener;
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
		//@RequestParam(value="action", required=true) String action
		String url = "";
		//validate the vendor  details
		//if(action.equals("Save")) {
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
//		}
//		if(action.equals("Cancel")) {
//			url = "redirect:/login";
//		}
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
		validator.validate(vendor, bindingResult);
		boolean status = profileService.updateVendor(vendor);
		if(status)
			return "/secure/login";
		else
			return "redirect:/vendor/update";
	}
}
