/*
 * Author: Yee Mon Zaw
 * Date: 28-Apr-2019
 * Class Name: VendorController
 * Package: mum.pmp.mstore.controller.profile
 * Description:  Vendor Sign up , update  
 * 
 */

package mum.pmp.mstore.controller.profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.pmp.mstore.config.CompanyCardConfig;
import mum.pmp.mstore.config.security.Listener;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.CreditCardValidator;
import mum.pmp.mstore.validator.VendorValidator;

@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private  VendorValidator validator;
	
	@Autowired
	private CreditCardValidator ccValidator;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private Listener sessionListener;
	
	@Autowired
	private CompanyCardConfig cards;
	
	// Vendor Sign up
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("creditCard", new CreditCard());
		System.out.println("In signup credit card");
		return "/profile/vendor_signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute Vendor vendor, BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) {
		String url = "";
		//validate the vendor  details
			validator.validate(vendor, bindingResult);
			
			ccValidator.validate(vendor.getCreditCard(), bindingResult);
			
			String paymentUrl = "";
			String fallbackUrl = ""; 
			
			if(bindingResult.hasErrors()) {
				url =  "/profile/vendor_signup";
			}else {
				if(profileService.signup(vendor, User_Type.VENDOR)) {
					int cardType = vendor.getCreditCard().getCardType();
					CreditCard c = new CreditCard();
					c.setCardName(vendor.getCreditCard().getCardName());
					c.setCardNumber(vendor.getCreditCard().getCardNumber());
					c.setCsv(vendor.getCreditCard().getCsv());
					
					String date = vendor.getCreditCard().getExpireDate();
					System.out.println("Expiry date" + date);
					
					LocalDate d = LocalDate.parse(date + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					String newDate = String.format("%tm", d) + "/" + String.format("%ty", d);
					System.out.println(">>>" + newDate);
					
					c.setExpireDate(newDate);
					
					CreditCard toCard = new CreditCard();
					
					if(cardType == 1)
					{ 
						toCard.setCardName(cards.getVisaCardName());
						toCard.setCardNumber(cards.getVisaCardNumber());
						toCard.setCsv(cards.getVisaCardCSV());
						toCard.setExpireDate(cards.getVisaCardexpireDate());
						
						paymentUrl = "/paymentgw/visa";
						fallbackUrl = fallbackUrl + "/vendor/visa/confirm";
					}
					else if(cardType == 2)
					{
						toCard.setCardName(cards.getMasterCardName());
						toCard.setCardNumber(cards.getMasterCardNumber());
						toCard.setCsv(cards.getMasterCardCSV());
						toCard.setExpireDate(cards.getMasterCardexpireDate());
						
						paymentUrl = "/paymentgw/master";
						fallbackUrl = fallbackUrl + "/vendor/master/confirm";
					}
					
						request.setAttribute("fromCardNumber", c.getCardNumber());
						request.setAttribute("fromCardName", c.getCardName());
						request.setAttribute("fromCardCSV", c.getCsv());
						request.setAttribute("fromCardExpireDate", c.getExpireDate());
						
						request.setAttribute("toCardNumber", toCard.getCardNumber());
						request.setAttribute("toCardName", toCard.getCardName());
						request.setAttribute("toCardCSV", toCard.getCsv());
						request.setAttribute("toCardExpireDate", toCard.getExpireDate());
						request.setAttribute("amount", 2500.00);
						
						request.setAttribute("fallbackUrl", fallbackUrl);
						return "forward:" + paymentUrl;
				}
				else {
					bindingResult.rejectValue("email", "vendor.email.exist");
					url =  "/profile/vendor_signup";
				}
			}
		return url;
	}
	
	@PostMapping("/{type}/confirm")
	public String paymentFallBack(@PathVariable String type, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		String status = (String) request.getAttribute("status");
		System.out.println("Fall back from payment gateway..." + status );
		return "redirect:/login";
	}
	
	@GetMapping("/update")
	public String updatePage(Model model) {
		Vendor vendorProfile = (Vendor) profileService.findByEmail(sessionListener.getUser().getEmail());
		model.addAttribute("vendor" , vendorProfile);
		return "/profile/vendor_profile";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Vendor vendor, BindingResult bindingResult) {
		validator.validate(vendor, bindingResult);
		boolean status = profileService.updateVendor(vendor);
		if(status)
			//return "/secure/login";
			return "redirect:/logout-success";
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
		return "redirect:/logout-success";
	}
}
