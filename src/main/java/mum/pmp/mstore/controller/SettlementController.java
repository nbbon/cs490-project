/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: SettlementController
 * Module: Payment
 * Description: The controller of Report module, responsible for settling order after payment confirmed.
 * It will send email to 
 * 		- Customer to confirm the successful of their order
 * 		- Vendors to inform Vendor order request for their shipping schedule 
 */

package mum.pmp.mstore.controller;

import java.time.LocalDate;
import java.util.Map.Entry;

import javax.mail.MessagingException;
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

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.email.EmailService;
import mum.pmp.mstore.service.settlement.SettlementService;

@Controller
@RequestMapping("/settlement")
public class SettlementController {

	@Autowired
	OrderService orderService;

	@Autowired
	SettlementService settlementService;
	
	@Autowired
	EmailService emailService;

	@PostMapping({"", "/"})
	public String settlement(RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("SETTLEMENT: start settlement processing");
		String orderNumber = ((Order) request.getSession().getAttribute("order")).getOrderNumber();
		Order order = orderService.getOrder(orderNumber);
		String status = "";
		if (order != null) {
			System.out.println("SETTLEMENT: sending notification to confirm for order number: " + order.getOrderNumber());
			order.setStatus("Confirmed");
			order.setOrderDate(LocalDate.now());
			orderService.save(order);
			for(Entry<String, String> entry : settlementService.buildInvoice(order).entrySet()) {
				try {
					System.out.println("SETTLEMENT: Send to: " + entry.getKey() + " - " + entry.getValue());
					emailService.sendEmail(entry.getValue());
				} catch (MessagingException e) {
					System.out.println(e.getMessage());
//					e.printStackTrace();
				}
			}
			
			status = "succeed";
		}

		redirectAttributes.addFlashAttribute("status", status);
		redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/settlement";
	}

	@GetMapping({"", "/"})
	public String displayResult(@ModelAttribute("status") String status, @ModelAttribute("order") Order order, Model model) {
		if(status.equals("succeed")) {
			model.addAttribute("status", "Order is confirmed!");
			model.addAttribute("message", "Thank you for shopping with us.");
			model.addAttribute("detail", "Your order number is: " + order.getOrderNumber() );
		} else {
			
		}
		return "settlement_result";
	}
}
