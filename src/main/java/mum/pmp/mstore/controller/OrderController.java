package mum.pmp.mstore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderFactory;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
//	@Autowired
	//OrderFactory orderFactory;
	
	

	@PostMapping("/create")
    public void createOrder(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws ServletException, IOException {
		
		ShoppingCart cart =(ShoppingCart) request.getAttribute("cart");
		
		Order order = OrderFactory.createOrder(cart);
		String getCustomerURL = "/get_customer";
		
		
		 Customer user = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     String email = user.getEmail();
		
		if (email!=null) {
			order.setCustomer(user);

          			
		}else {
		String	customerURL = "/customer/create";
			
		RequestDispatcher rd=	request.getRequestDispatcher(getCustomerURL);
		request.setAttribute("order", order);
		rd.forward(request, response);
		}
	
		
		
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
		return "profile/customer_profile"; // customer.html
	}
	
	@PostMapping("/shipping")
    public String getShipping(@RequestParam Order order, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/shopping_address"; 
	}
	
	@GetMapping("/shopping_address")
	public String getShippinAddress(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		return "shopping_address"; // shopping_address.html
	}
	
	@PostMapping("/billing")
    public String getBilling(@RequestParam Order order, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/billing"; 
	}
	
	@GetMapping("/billing")
	public String getBillingAddress(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		return "billing"; // billing.html
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
