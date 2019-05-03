package mum.pmp.mstore.integration.mockpayment;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.integration.mockpayment.service.MockPaymentService;
import mum.pmp.mstore.model.CreditCard;

@Controller
public class MockPaymentGatewayController {
	@Autowired
	MockPaymentService mockPaymentService;

	@PostMapping("paymentgw/master")
	public void requestForMasterPayment(@RequestParam CreditCard card, @RequestParam String orderNumber,
			@RequestParam Double amount, HttpServletResponse response) {
		Boolean result = false;
		if (verifyCardInfo(card)) {
			
			String description = LocalDate.now() + ";" + card.getCardName().toUpperCase().replace(" ", "") + "ORDER:"
				+ orderNumber + ";amount=" + amount;
			if(mockPaymentService.processMasterCardPaymentRequest(card.getCardNumber(), card.getCardName(), card.getCsv(),
				card.getExpireDate(), amount, description) != null) {
				System.out.println("PAYMENT GATEWAY: resquest approved for " + card.getCardNumber());
				System.out.println("PAYMENT GATEWAY: transaction is approved: " + description);
				result = true;
			}
		}
		
		try {
			response.setContentType("text/html");
			if(result) {
				response.getWriter().println("Request Approved");
			} else {
				System.out.println("PAYMENT GATEWAY: resquest declined for " + card.getCardNumber());
				response.getWriter().println("Request declined");
			}
		} catch (IOException e) {
			System.out.println("PAYMENT GATEWAY: " + e.getMessage());
			// e.printStackTrace();
		}
		return;
	}

	@PostMapping("paymentgw/visa")
	public void requestForVisaPayment(@RequestParam CreditCard card, @RequestParam String orderNumber,
			@RequestParam Double amount, HttpServletResponse response) {
		Boolean result = false;
		if (verifyCardInfo(card)) {
			String description = LocalDate.now() + ";" + card.getCardName().toUpperCase().replace(" ", "") + "ORDER:"
				+ orderNumber + ";amount=" + amount;
			if(mockPaymentService.processVisaCardPaymentRequest(card.getCardNumber(), card.getCardName(), card.getCsv(),
				card.getExpireDate(), amount, description) != null) {
				System.out.println("PAYMENT GATEWAY: resquest approved for " + card.getCardNumber());
				System.out.println("PAYMENT GATEWAY: transaction is approved: " + description);
				result = true;
			}
		}
		
		try {
			response.setContentType("text/html");
			if(result) {
				response.getWriter().println("Request Approved");
			} else {
				System.out.println("PAYMENT GATEWAY: resquest declined for " + card.getCardNumber());
				response.getWriter().println("Request declined");
			}
		} catch (IOException e) {
			System.out.println("PAYMENT GATEWAY: " + e.getMessage());
			// e.printStackTrace();
		}
		return;
	}
	
	private Boolean verifyCardInfo(CreditCard card) {
		if (card.getCardNumber().length() != 16) {
			System.out.println("PAYMENT GATEWAY: Invalid card number");
			return false;
		}
		
		if (card.getCsv().length() != 3) {
			System.out.println("PAYMENT GATEWAY: Invalid csv number");
			return false;
		}

		if (!verifyCardExpiryDate(card.getExpireDate())) {
			System.out.println("PAYMENT GATEWAY: Failed to verify card expiry date");
			return false;
		}

		return true;
	}

	private Boolean verifyCardExpiryDate(String expiryDate) {
		return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
	}

}
