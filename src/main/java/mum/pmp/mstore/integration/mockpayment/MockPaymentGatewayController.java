/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: MockPaymentController
 * Module: Mock Payment
 * Description: The main controller of Mock Payment module, responsible for controlling the payment request
 * from Payment Module, Vendor Module. When a payment request come, it will do some validations on requested
 * credit cards before pass it to MockPaymentService to perform making payment transactions 
 * 
 */

package mum.pmp.mstore.integration.mockpayment;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.integration.mockpayment.service.MockPaymentService;

@Controller
public class MockPaymentGatewayController {
	@Autowired
	MockPaymentService mockPaymentService;

	@PostMapping("paymentgw/master")
	public void requestForMasterPayment(HttpServletRequest request, HttpServletResponse response) {
		String fromCardNumber = (String) request.getAttribute("fromCardNumber");
		String fromCardName = (String) request.getAttribute("fromCardName");
		String fromCardCSV = (String) request.getAttribute("fromCardCSV");
		String fromCardExpireDate = (String) request.getAttribute("fromCardExpireDate");
		String toCardNumber = (String) request.getAttribute("toCardNumber");
		String toCardName = (String) request.getAttribute("toCardName");
		String toCardCSV = (String) request.getAttribute("toCardCSV");
		String toCardExpireDate = (String) request.getAttribute("toCardExpireDate");
		Double amount = (Double) request.getAttribute("amount");
		String fallbackUrl = (String) request.getAttribute("fallbackUrl");
		Boolean result = false;
		
		System.out.println("PAYMENT GATEWAY: payment resquest ");
		System.out.println("From card");
		System.out.println("\tNumber: " + fromCardNumber);
		System.out.println("\tName: " + fromCardName); 
		System.out.println("\tCSV "+ fromCardCSV);
		System.out.println("\tExpire Date: " + fromCardExpireDate);
		System.out.println("To card");
		System.out.println("\tNumber: " + toCardNumber);
		System.out.println("\tName: " + toCardName); 
		System.out.println("\tCSV "+ toCardCSV);
		System.out.println("\tExpire Date: " + toCardExpireDate);
		if (verifyCardInfo(fromCardNumber, fromCardName, fromCardCSV, fromCardExpireDate)
			&& verifyCardInfo(toCardNumber, toCardName, toCardCSV, toCardExpireDate)) {

			String description = "DATE:" + LocalDate.now() + "," + fromCardName.toUpperCase() + ";ORDER:"
					+ ";AMOUNT=" + amount;
			if (mockPaymentService.processMasterCardPaymentRequest(fromCardNumber, fromCardName, fromCardCSV,
					fromCardExpireDate, toCardNumber, toCardName, toCardCSV, toCardExpireDate, amount,
					description)) {
				System.out.println("PAYMENT GATEWAY: payment resquest approved for " + fromCardNumber);
				System.out.println("PAYMENT GATEWAY: transaction is approved: " + description);
				result = true;
			}
		} else {
			System.out.println("Cannot verified credit card!!");
		}

		try {
			if (result) {
				request.setAttribute("status", "approved");
			} else {
				System.out.println("PAYMENT GATEWAY: resquest declined for " + fromCardNumber);
				request.setAttribute("status", "declined");
			}
			
			System.out.println("PAYMENT GATEWAY: fall back to the caller at: " + fallbackUrl);

			RequestDispatcher rd = request.getRequestDispatcher(fallbackUrl);
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	@PostMapping("paymentgw/visa")
	public void requestForVisaPayment(HttpServletRequest request, HttpServletResponse response) {
		String fromCardNumber = (String) request.getAttribute("fromCardNumber");
		String fromCardName = (String) request.getAttribute("fromCardName");
		String fromCardCSV = (String) request.getAttribute("fromCardCSV");
		String fromCardExpireDate = (String) request.getAttribute("fromCardExpireDate");
		String toCardNumber = (String) request.getAttribute("toCardNumber");
		String toCardName = (String) request.getAttribute("toCardName");
		String toCardCSV = (String) request.getAttribute("toCardCSV");
		String toCardExpireDate = (String) request.getAttribute("toCardExpireDate");
		Double amount = (Double) request.getAttribute("amount");
		String fallbackUrl = (String) request.getAttribute("fallbackUrl");
		Boolean result = false;
		if (verifyCardInfo(fromCardNumber, fromCardName, fromCardCSV, fromCardExpireDate) 
			&& verifyCardInfo(toCardNumber, toCardName, toCardCSV, toCardExpireDate)) {

			String description = "DATE:" + LocalDate.now() + "," + fromCardName.toUpperCase()
					+ ";AMOUNT=" + amount;
			if (mockPaymentService.processVisaCardPaymentRequest(fromCardNumber, fromCardName, fromCardCSV,
					fromCardExpireDate, toCardNumber, toCardName, toCardCSV, toCardExpireDate, amount,
					description)) {
				System.out.println("PAYMENT GATEWAY: payment resquest approved for " + fromCardNumber);
				System.out.println("PAYMENT GATEWAY: transaction is approved: " + description);
				result = true;
			}
		}

		try {
			if (result) {
				request.setAttribute("status", "approved");
			} else {
				System.out.println("PAYMENT GATEWAY: resquest declined for " + fromCardNumber);
				request.setAttribute("status", "declined");
			}
			
			System.out.println("PAYMENT GATEWAY: fall back to the caller at: " + fallbackUrl);

			RequestDispatcher rd = request.getRequestDispatcher(fallbackUrl);
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	private Boolean verifyCardInfo(String cardNumber, String cardName, String csv, String expireDate) {
		if (cardNumber.length() != 16) {
			System.out.println("PAYMENT GATEWAY: Invalid card number");
			return false;
		}

		if (csv.length() != 3) {
			System.out.println("PAYMENT GATEWAY: Invalid csv number");
			return false;
		}

		if (!verifyCardExpiryDate(expireDate)) {
			System.out.println("PAYMENT GATEWAY: Failed to verify card expiry date");
			return false;
		}

		return true;
	}

	private Boolean verifyCardExpiryDate(String expiryDate) {
		return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
	}
	

}
