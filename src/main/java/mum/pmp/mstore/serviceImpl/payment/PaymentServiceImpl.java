package mum.pmp.mstore.serviceImpl.payment;

import org.springframework.beans.factory.annotation.Autowired;

import mum.pmp.mstore.integration.MockPaymentGateway;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.service.payment.PaymentService;

public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	MockPaymentGateway paymentGateway;

	@Override
	public Boolean confirmPayment(CreditCard credit, String orderNumber, double amount) {
		System.out.println("Sending payment request for orderNmuber=" + orderNumber);
		Boolean accepted = paymentGateway.requestPayment(credit, orderNumber, amount);
		if(accepted) {
			System.out.println("Payment request accepted for orderNmuber=" + orderNumber);
		} else {
			System.out.println("Payment request rejected for orderNmuber=" + orderNumber);
		}
		return accepted;
	}

}
