package mum.pmp.mstore.controller.profile;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.ProfileService;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/admins")
	public String getAdminList(Model model) {
		List<Admin> admins = profileService.findNewAdmins();
		System.out.println(admins);
		model.addAttribute("admins", admins);
		return "/approval/adminList";
	}

	@PostMapping("/admins/{adminEmail}")
	public String approveAdmin(//@PathVariable("adminEmail") String adminEmail, 
			@RequestParam(value="action", required=true) String action, @RequestParam(value="admin-email", required=true) String adminEmail) throws AddressException, MessagingException {
		System.out.println("adminEmail :" + adminEmail);
		profileService.approveAdmin(adminEmail, action);
		return "redirect:/approval/admins";
	}
	
	@GetMapping("/vendors")
	public String getVendorList(Model model) {
		List<Vendor> vendors = profileService.findNewVendors();
		model.addAttribute("vendors", vendors);
		return "/approval/vendorList";	
	}
	
	@PostMapping("/vendors/{vendorEmail}")
	public String approveVendor(//@PathVariable("vendorEmail") String vendorEmail, 
			@RequestParam(value="action", required=true) String action , @RequestParam(value="vendor-email", required=true) String vendorEmail) throws AddressException, MessagingException {
		System.out.println("In vendor approval..");
		profileService.approveVendor(vendorEmail, action);
		return "redirect:/approval/vendors";
	}
}
