package mum.pmp.mstore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.security.ProfileService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
    ProfileService	profileService;
	
	@Autowired
	private MyAuthSuccessHandler handler;
	
	@PostMapping("/create")
    public String createOrder(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws ServletException, IOException {
		
		ShoppingCart cart =(ShoppingCart) request.getAttribute("Shopping_Cart");
		System.out.println("cart details : " + cart);
		
		Order order = OrderFactory.createOrder(cart);
		String getCustomerURL = "/get_customer";
		
		String getUserURL="/order/signup";
		String getShippingURL= "/shopping_address";
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = handler.getAuth();
		Boolean isCus = false;
		if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
			for (GrantedAuthority roles : auth.getAuthorities()) {
				String authorizedRole = roles.getAuthority();
				if(authorizedRole.equals("ROLE_CUSTOMER")) {
					UserDetails user = (UserDetails) auth.getPrincipal();
					Profile customer = profileService.findByEmail(user.getUsername());
					if(customer != null) {
						order.setCustomer((Customer) customer);
						return "forward:/shipping";
					}
				}
			}
		}
		
		if(!isCus) {
			if (auth == null ) {
				
				//RequestDispatcher rd=request.getRequestDispatcher(getCustomerURL);
				request.setAttribute("order", order);
  			   // rd.forward(request, response);
  			   // return getShippingURL;
  			    return "forward:" + getCustomerURL;
			}
			
			if (auth.getPrincipal().equals("anonymousUser")) {
				
				//RequestDispatcher rd=request.getRequestDispatcher(getUserURL);
				request.setAttribute("order", order);
  			    //rd.forward(request, response);
				
  			    //return getShippingURL;
  			    return "forward:" + getUserURL;
				
			}
			
			// Show UI to ask customer
			// If they want to chackout with logged user
			// OR checkout as guest
			// If user chooses to checkout as logged user
			//    --> show login page
			//    --> if user successful logged in
			//	  --> get user from
			// else	
			//    --> show UI to get customer info
			//	  --> create new Customer obj
		}
		return getShippingURL;
			
		
	}	
	
	@PostMapping("/signup")
	public String adminSignupPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("customer", new Customer());
		return "/profile/customer_signup";
	}
	
	@PostMapping("/get_customer")
    public String trggerGetCustomer(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		Order order =(Order) request.getAttribute("order");
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/customer";  
	}
	
	@GetMapping("/customer")
	public String getCustomer(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		System.out.println("^^^^^^^");
		return "profile/customer_signup"; // customer.html
	}
	
	@PostMapping("/shipping")
    public String getShipping(@RequestParam Order order, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/shopping_address"; 
	}
	
	@GetMapping("/shopping_address")
	public String getShippinAddress(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		return "order/shopping_address"; // shopping_address.html
	}
	
	@PostMapping("/billing")
    public String getBilling(@RequestParam Order order, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
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
			
		RequestDispatcher rd=	request.getRequestDispatcher(paymetURL);
		request.setAttribute("order", order);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}	
}
