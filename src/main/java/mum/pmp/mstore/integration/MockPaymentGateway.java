package mum.pmp.mstore.integration;

import org.springframework.stereotype.Component;

import mum.pmp.mstore.model.CreditCard;

@Component
public class MockPaymentGateway {

	public Boolean requestPayment(CreditCard card, String orderNumber, double amount) {
		if(!verifyCardNumber(card)) return false;
		if(!checkBalance(amount)) return false;
		System.out.println("PAYMENT GATEWAY: Accepted payment resquest:" + amount);
		System.out.println("/tAmount of: " + amount);
		System.out.println("/tNote: " + orderNumber);
		return true;
	}
	
	private Boolean verifyCardNumber(CreditCard card) {
		if(card.getCardNumber().length() != 16) {
			System.out.println("PAYMENT GATEWAY: Invalid card number");
			return false;
		}
		
		if(!verifyCardExpiryDate(card.getValidationDate())) {
			System.out.println("PAYMENT GATEWAY: Failed to verify card expiry date");
			return false;
		}
		
		return true;
	}
	
	private Boolean verifyCardExpiryDate(String expiryDate) {
	    return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
	}
	
	private Boolean checkBalance(double amount) {
		if(amount <=0 ) {
			System.out.println("PAYMENT GATEWAY: Failed to check balance");
			return false;
		}
		
		return true;
	}

}
