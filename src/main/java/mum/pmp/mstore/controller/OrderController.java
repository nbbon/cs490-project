package mum.pmp.mstore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.pmp.mstore.config.security.Listener;
import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderFactory;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.validator.AdminValidator;
import mum.pmp.mstore.validator.CreditCardValidator;
import mum.pmp.mstore.validator.CustomerValidator;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	ProfileService profileService;
	
	@Autowired
	private CustomerValidator validator;
	
	@Autowired
	private CreditCardValidator ccValidator;

	private Customer getLoggedCustomer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
			for (GrantedAuthority roles : auth.getAuthorities()) {
				String authorizedRole = roles.getAuthority();
				if (authorizedRole.equals("ROLE_CUSTOMER")) {
					UserDetails user = (UserDetails) auth.getPrincipal();
					return (Customer) profileService.findByEmail(user.getUsername());
				}
			}
		}
		return null;
	}

	@PostMapping("/create")
	public String createOrder(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("Shopping_Cart");

		Order order = OrderFactory.createOrder(cart);
		String getCustomerURL = "/get_customer";

		String getUserURL = "/signup";
		String getShippingURL = "/shopping_address";

		Customer loggedCustomer = getLoggedCustomer();
		String targetURL = "";
		
		if(loggedCustomer != null) {
			order.setCustomer(loggedCustomer);
			targetURL = "forward:/order/shipping"; 
			request.getSession().setAttribute("order", order);
		} else {
			redirectAttributes.addFlashAttribute("order", order);
			targetURL = "redirect:/customer";
			// Show UI to ask customer
			// If they want to chackout with logged user
			// OR checkout as guest
			// If user chooses to checkout as logged user
			// --> show login page
			// --> if user successful logged in
			// --> get user from
			// else
			// --> show UI to get customer info
			// --> create new Customer obj
		}
		
		return targetURL;

	}

	@GetMapping("/signup")
	public String adminSignupPage(Model model) {
		model.addAttribute("admin", new Admin());
		return "/profile/admin_signup";
	}

	@PostMapping("/customer")
	public String showCustomerForm(@ModelAttribute Order order, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		Customer customer = order.getCustomer();
		validator.validate(customer, bindingResult);
		
		ccValidator.validate(customer.getCreditCard(), bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "/customer";
		}
		
		redirectAttributes.addFlashAttribute("order", order);
		return "forward:/shipping";
	}

	@GetMapping("/customer")
	public String getCustomer(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		model.addAttribute("customer", order.getCustomer());
		return "/customer_form"; // customer.html
	}

	@PostMapping("/shipping")
	public String getShipping(RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request) {
		Order order = (Order) request.getSession().getAttribute("order");
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/shopping_address";
	}

	@GetMapping("/shopping_address")
	public String getShippinAddress(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		return "order/shopping_address"; // shopping_address.html
	}

	@PostMapping("/billing")
	public String getBilling(@RequestParam Order order, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/billing";
	}

	@GetMapping("/billing")
	public String getBillingAddress(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		return "order/billing"; // billing.html
	}

	@PostMapping("/sendToPayment")
	public void sendToPayment(@RequestParam Order order, HttpServletRequest request, HttpServletResponse response) {

		String paymetURL = "/payment";

		RequestDispatcher rd = request.getRequestDispatcher(paymetURL);
		request.setAttribute("order", order);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}
}
