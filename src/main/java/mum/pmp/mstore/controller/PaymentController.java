/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: PaymentController
 * Module: Payment
 * Description: The main controller of Payment module, reponsible for controlling the order payment request
 * 		When receive order payment request from Order Module. It will send payment request with 
 * credit card info to Mock Payment gateway for making payment transaction. After payment transactions 
 * made return from the gateway. It will send request to Settlement for handling fulfill the order
 * 
 */

package mum.pmp.mstore.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.pmp.mstore.config.CompanyCardConfig;
import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.service.OrderService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	OrderService orderService;

	@Autowired
	private CompanyCardConfig cards;
	
	@PostMapping({"", "/" })
	public String processPayment(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String orderNumber = ((Order) request.getSession().getAttribute("order")).getOrderNumber(); 
		Order order = orderService.getOrder(orderNumber);
		System.out.println("PAYMENT: Start payment processing - " + orderNumber);
		if (order != null && order.getCreditCard() != null) {
			System.out.println("PAYMENT: Receiving payment request ");
			String paymentGatewayUrl = "";
			String fallbackUrl = ""; // "http://localhost:8080/payment";
			
			// Create Order number and save to DB
			order.setStatus("pending");
			System.out.println(order.getOrderNumber());
			orderService.save(order);
			
			CreditCard fromCard = order.getCreditCard();
			CreditCard toCard = new CreditCard();
			if (fromCard.getCardType() == 1) { // Visa
				paymentGatewayUrl = "/paymentgw/visa";
				fallbackUrl = fallbackUrl + "/payment/visa/confirm/" + order.getOrderNumber();
				
				toCard.setCardName(cards.getVisaCardName());
				toCard.setCardNumber(cards.getVisaCardNumber());
				toCard.setCsv(cards.getVisaCardCSV());
				toCard.setExpireDate(cards.getVisaCardexpireDate());
			} else {
				paymentGatewayUrl = "/paymentgw/master";
				fallbackUrl = fallbackUrl + "/payment/master/confirm/" + order.getOrderNumber();
				
				toCard.setCardName(cards.getMasterCardName());
				toCard.setCardNumber(cards.getMasterCardNumber());
				toCard.setCsv(cards.getMasterCardCSV());
				toCard.setExpireDate(cards.getMasterCardexpireDate());
			}
			
			LocalDate d = LocalDate.parse(fromCard.getExpireDate() + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String fromCardExpireDate = String.format("%tm", d) + "/" + String.format("%ty", d);
			request.setAttribute("fromCardNumber", fromCard.getCardNumber());
			request.setAttribute("fromCardName", fromCard.getCardName());
			request.setAttribute("fromCardCSV", fromCard.getCsv());
			request.setAttribute("fromCardExpireDate", fromCardExpireDate);
			request.setAttribute("toCardNumber", toCard.getCardNumber());
			request.setAttribute("toCardName", toCard.getCardName());
			request.setAttribute("toCardCSV", toCard.getCsv());
			request.setAttribute("toCardExpireDate", toCard.getExpireDate());
			request.setAttribute("amount", order.getTotalPrice());
			request.setAttribute("fallbackUrl", fallbackUrl);
			
			System.out.println("PAYMENT: send payment request to payment gateway");
			return "forward:" + paymentGatewayUrl;
		} else {
			model.addAttribute("status", "Invalid Order");
			return "payment_error";
		}
	}

	@PostMapping("/{type}/confirm/{orderNumber}")
	public String paymentFallBack(@PathVariable String type, @PathVariable String orderNumber, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		String status = (String) request.getAttribute("status");
		System.out.println("PAYMENT: Fall back from payment gateway..." + status + "," + orderNumber);
		// redirectAttributes.addFlashAttribute("orderNumber", orderNumber);
		String targetURL = "";
		if (status.equals("approved")) {
			System.out.println("PAYMENT: payment approved for order number " + orderNumber);
			Order order = orderService.getOrder(orderNumber);
			order.setStatus("Paid");
			order.setOrderDate(LocalDate.now());
			order = orderService.save(order);
			request.getSession().setAttribute("order", order);
			targetURL = "forward:/settlement";
		} else {
			System.out.println("PAYMENT: payment declined for order number " + orderNumber);
			orderService.remove(orderNumber);
			redirectAttributes.addFlashAttribute("status", status);
			targetURL = "redirect:/payment";
		}
		return targetURL;
	}

//	@PostMapping("")
//	public String paymentErrorHandler(RedirectAttributes redirectAttributes, HttpServletRequest request,
//			HttpServletResponse response) {
//		redirectAttributes.addFlashAttribute("status", (String) request.getAttribute("status"));
//		return "redirect:/payment";
//	}

	@GetMapping({"", "/"})
	public String displayError(@ModelAttribute("status") String status, Model model) {
		System.out.println("Get back from payment gateway..." + status);
		if (status.equals("approved")) {
			model.addAttribute("status", "Order successfull!");
			model.addAttribute("message", "Thank you for shopping at Online Branded Shopping.");
//			model.addAttribute("detail", "Your order number is: " + orderNumber);
		} else {
			model.addAttribute("status", "Order failed!");
			model.addAttribute("message", "Payment is not success.");
			model.addAttribute("detail", "Please recheck your payment information");
		}

		return "payment_error";
	}
	
}
