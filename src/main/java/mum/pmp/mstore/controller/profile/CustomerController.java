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
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.ProductService;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.utilities.User_Type;
import mum.pmp.mstore.validator.CreditCardValidator;
import mum.pmp.mstore.validator.CustomerValidator;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private Listener sessionListener;
	
	@Autowired
	private CustomerValidator validator;
	
	@Autowired
	private CreditCardValidator ccValidator;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private CompanyCardConfig cards;
	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "/profile/customer_signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute Customer customer, BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) {
		
		String url = "";
		System.out.println("bindingResult: "+ bindingResult.getClass());
		//Validate the customer details.
		validator.validate(customer, bindingResult);
		
		ccValidator.validate(customer.getCreditCard(), bindingResult);
		String paymentUrl = "";
		String fallbackUrl = ""; // "http://localhost:8080/payment";
		
		if(bindingResult.hasErrors()) {
			url = "/profile/customer_signup";
		}
		else {
			if(profileService.signup(customer, User_Type.CUSTOMER)) {
				
				int cardType = customer.getCreditCard().getCardType();
				CreditCard c = new CreditCard();
				c.setCardName(customer.getCreditCard().getCardName());
				c.setCardNumber(customer.getCreditCard().getCardNumber());
				c.setCsv(customer.getCreditCard().getCsv());
				
				String date = customer.getCreditCard().getExpireDate();
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
				bindingResult.rejectValue("email", "There is already an account registered with that email");
				url = "/profile/customer_signup";
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
		Customer customerProfile = (Customer) profileService.findByEmail(sessionListener.getUser().getEmail());

		System.out.println("email in update sessionListener.getUser().getEmail(): "+sessionListener.getUser().getEmail());
		model.addAttribute("customer", customerProfile);
		return "/profile/customer_profile";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Customer customer, BindingResult bindingResult) {
		
		//validate the admin details.
		validator.validate(customer, bindingResult);
		boolean status = profileService.updateCustomer(customer);
		if(status)
			return "/secure/login";
		else
			return "redirect:/customer/update";
	}
	
	@PostMapping("/disable")
	public String disableCustomer(@ModelAttribute("customer") Customer customer)
	{
		System.out.println(customer);
		System.out.println(customer.getEmail());
		
		Profile profile = profileService.findByEmail(customer.getEmail());
		System.out.println("My Profile: "+profile);
		byte status = 0;
		profile.setStatus(status);
		profileService.saveProfile(profile);
		return "redirect:/login";
	}
	
}
