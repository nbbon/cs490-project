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
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.ProfileService;

@Controller
public class ApprovalController {

	@Autowired
	private ProfileService profileService;

	/*	Admin Approval by Super Admin 
	 * */
	 
	@GetMapping("/super/admins")
	public String getAdminList(Model model) {
		List<Admin> admins = profileService.findNewAdmins();
		System.out.println(admins);
		model.addAttribute("admins", admins);
		return "/approval/adminList";
	}

	@PostMapping("/admin/approve/{adminEmail}")
	public String approveAdmin(@PathVariable("adminEmail") String adminEmail, @RequestParam(value="action", required=true) String action) throws AddressException, MessagingException {
		profileService.approveAdmin(adminEmail, action);
		return "redirect:/super/admins";
	}
	
	@GetMapping("/admin/delete/{adminId}")
	public String deletePerson(Admin person, @PathVariable("adminId") long adminId) {
		Admin personToDelete = (Admin) profileService.findById(adminId);
		//personService.removePerson(personToDelete);
		return "redirect:/admins";
	}
	
	@GetMapping("/admin/{adminId}")
	public String updatePerson(@PathVariable("adminId") Long adminId, Model model) {
		model.addAttribute("person", profileService.findById(adminId));
		return "admins";
	}
	
	
	/* Vendor Approval by Admin */
	@GetMapping("/admin/vendors")
	public String getVendorList(Model model) {
		List<Vendor> vendors = profileService.findNewVendors();
		model.addAttribute("vendors", vendors);
		return "/approval/vendorList";	
	}
	
	@PostMapping("/vendor/approve/{vendorEmail}")
	public String approveVendor(@PathVariable("vendorEmail") String vendorEmail, @RequestParam(value="action", required=true) String action) throws AddressException, MessagingException {
		System.out.println("In vendor approval..");
		profileService.approveVendor(vendorEmail, action);
		return "redirect:/admin/vendors";
	}
}
