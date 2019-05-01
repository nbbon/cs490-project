package mum.pmp.mstore.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.config.security.Listener;
import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.AdminValidator;

@Controller
public class AdminController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private AdminValidator validator;

	@Autowired
	private Listener sessionListener;

	@GetMapping("/admin/signup")
	public String adminSignupPage(Model model) {
		model.addAttribute("admin", new Admin());
		return "/profile/admin_signup";
	}

	@PostMapping("/admin/signup")
	public String adminSignup(@ModelAttribute Admin admin, BindingResult bindingResult) {
		String url = "";
		// validate the admin details.
		validator.validate(admin, bindingResult);
		if (bindingResult.hasErrors()) {
			url = "/profile/admin_signup";
		} else {
			if (profileService.signup(admin, User_Type.ADMIN)) {
				url = "redirect:/login";
			} else {
				bindingResult.rejectValue("email", "There is already an account registered with that email.");
				url = "/profile/admin_signup";
			}
		}
		return url;
	}

	@GetMapping("/admin/update")
	public String updatePage(Model model) {
		Admin adminProfile = (Admin) profileService.findByEmail(sessionListener.getUser().getEmail());
		model.addAttribute("admin", adminProfile);
		return "/profile/admin_profile";
	}

	@PostMapping("/admin/update")
	public String update(@ModelAttribute Admin admin, BindingResult bindingResult) {

		// validate the admin details.
		validator.validate(admin, bindingResult);

		Profile person = profileService.findByEmail(admin.getEmail());
		Admin adminToUpdate;
		if (person instanceof Admin) {
			adminToUpdate = (Admin) person;
			adminToUpdate.setFirstName(admin.getFirstName());
			adminToUpdate.setLastName(admin.getLastName());
			adminToUpdate.setPassword(admin.getPassword());
			adminToUpdate.setPhone(admin.getPhone());
			profileService.saveProfile(adminToUpdate);
			
			//need to update password ins user table.
		}
		return "redirect:/admin/update";
	}
	
}
