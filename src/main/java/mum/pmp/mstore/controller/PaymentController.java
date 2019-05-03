package mum.pmp.mstore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.service.payment.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/")
	public String processPayment(@ModelAttribute("order") Order order, Model model,HttpServletRequest request, 
	        HttpServletResponse response) {
		if(order != null) {
			String paymentUrl = "";
			CreditCard card = order.getCreditCard();
			if(card.getCardType() == 1) { // Visa
				paymentUrl = "/paymentgw/visa";
			} else {
				paymentUrl = "/paymentgw/master";
			}
		    try {
		    	RequestDispatcher rd = request.getRequestDispatcher(paymentUrl);
				rd.forward(request, response);
				return ""; // Thank you page
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
