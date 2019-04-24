package mum.pmp.mstore.service.payment;

import mum.pmp.mstore.model.CreditCard;

public interface PaymentService {
	public Boolean confirmPayment(CreditCard credit, String orderNumber, double amount);
}
