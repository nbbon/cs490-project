/*
 * Author: Yee Mon Zaw
 * Date: 01-May-2019
 * Class Name: ApprovalController
 * Package: mum.pmp.mstore.controller.profile
 * Description: Approval Controller to approve admins and verndors. 
 * Scenario 1) Super Admin can view the list of pending admins and once super admin approved the selected admin, Admin can be able  to login. 
 * 			2) Admin can view  the  list of vendors and once he approved, vendor will recieve the email and able to login.
 * 
 */

package mum.pmp.mstore.controller.profile;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//Display all pending admin accounts for super admin to approve. 
	@GetMapping("/admins")
	public String getAdminList(Model model) {
		List<Admin> admins = profileService.findNewAdmins();
		model.addAttribute("admins", admins);
		return "/approval/adminList";
	}

	//Super Admin will approve admins. 
	@PostMapping("/admins/{adminEmail}")
	public String approveAdmin(//@PathVariable("adminEmail") String adminEmail, 
			@RequestParam(value="action", required=true) String action, @RequestParam(value="admin-email", required=true) String adminEmail) throws AddressException, MessagingException {
		profileService.approveAdmin(adminEmail, action);
		return "redirect:/approval/admins";
	}
	
	//Display all pending vendor accounts for Admin to approve. 
	@GetMapping("/vendors")
	public String getVendorList(Model model) {
		List<Vendor> vendors = profileService.findNewVendors();
		model.addAttribute("vendors", vendors);
		return "/approval/vendorList";	
	}
	
	// Admin will approve vendors.
	@PostMapping("/vendors/{vendorEmail}")
	public String approveVendor(//@PathVariable("vendorEmail") String vendorEmail, 
			@RequestParam(value="action", required=true) String action , @RequestParam(value="vendor-email", required=true) String vendorEmail) throws AddressException, MessagingException {
		profileService.approveVendor(vendorEmail, action);
		return "redirect:/approval/vendors";
	}
}
