package mum.pmp.mstore.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.pmp.mstore.config.security.MyAuthSuccessHandler;
import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderFactory;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.security.ProfileService;

import mum.pmp.mstore.validator.CreditCardValidator;
import mum.pmp.mstore.validator.CustomerValidator;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private MyAuthSuccessHandler handler;
	
	@Autowired
	OrderService orderService;

	@Autowired
	ProfileService profileService;

	@Autowired
	private CustomerValidator validator;

	@Autowired
	private CreditCardValidator ccValidator;

	private Customer getLoggedCustomer() {
		System.out.println("handler" + handler);
		Authentication auth = handler.getAuth();
		System.out.println("auth:" + auth);
		Boolean isCus = false;
		
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
	public String createOrder(RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		System.out.println("ORDER: Request to create order");

		ShoppingCart cart = (ShoppingCart) request.getAttribute("Shopping_Cart");

		Order order = OrderFactory.createOrder(cart);

		Customer loggedCustomer = getLoggedCustomer();
		if (loggedCustomer != null) {
			order.setCustomer(loggedCustomer);
			order.setCreditCard(loggedCustomer.getCreditCard());

			redirectAttributes.addFlashAttribute("order", order);
			System.out.println("ORDER: User logged in redirect to /order/customer");
			return "redirect:/order/customer"; // get shipping & billing addresses
		}

		request.setAttribute("order", order);
		return "forward:/order/checkLoggedin"; // security check and login
	}

	@PostMapping("/checkLoggedin")
	public String checkLoggedin(@RequestParam(required = false) Boolean guest,
			RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		System.out.println("ORDER: Security check for Guest is " + guest);
		
		Order order = (Order) request.getAttribute("order");
		if(guest != null && guest == true) {
			System.out.println("ORDER: redirect to /order/guest/detail");
			redirectAttributes.addFlashAttribute("order", order);
			return "redirect:/order/guest/detail"; // to get guest detail info
		}
		
		System.out.println("ORDER: forward to /order/customer");
		return "forward:/order/customer"; // security check and getting addresses
		
	}

	@GetMapping("/guest/detail")
	public String checkGuestForm(@ModelAttribute("order") Order order, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		System.out.println("ORDER: Guest checkout get detail");
		
		model.addAttribute("order", order);
		return "order/guest_detail";  // create html form to get GUEST info: guest user name, credit card, shipping and billing addr
	}

	@PostMapping("/guest/detail")
	public String getGuestInfo(@ModelAttribute Order order, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		Customer customer = order.getCustomer();
		validator.validate(customer, bindingResult);

		ccValidator.validate(customer.getCreditCard(), bindingResult);

		if (bindingResult.hasErrors()) {
			return "/guest/detail";
		}

		request.setAttribute("order", order);
		return "forward:/payment";
	}

	@PostMapping("/customer")
	public String customerCheckout(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		System.out.println("ORDER: do security check before go further");
		Order order = (Order) request.getAttribute("order");
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/order/customer"; // get shipping & billing addresses
		
	}
	
	@PostMapping("/saveCustomerAddress")
	public String saveCustomerAddress(@ModelAttribute Order order, BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		Customer customer = order.getCustomer();
		if (bindingResult.hasErrors()) {
			return "redirect:/customer";
		}

		request.setAttribute("order", order);
		return "forward:/payment";
		
	}
	
	@GetMapping("/customer")
	public String getAddressesForm(@ModelAttribute("order") Order order, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
//		Order order = (Order) request.getAttribute("order");
		System.out.println("ORDER: security checked and user successful logged in");
		Customer loggedCustomer = getLoggedCustomer();
		if (loggedCustomer != null) {
			order.setCustomer(loggedCustomer);
			order.setCreditCard(loggedCustomer.getCreditCard());
			
			System.out.println("ORDER: Get customer addresses");
			model.addAttribute("order", order);
		    System.out.println("order:" + order);
			return "order/shipping_billing_addresses"; // create html form for shipping & billing addr
		} else {
			System.out.println("ORDER: Something is going wrong here");
		}
		
		System.out.println("ORDER: Not supose to go here. If it's then go back for security check");
		return "Error";
	}

}
