/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: PaymentService
 * Module: Payment
 * Description: The interface, define necessary methods for payment service  
 * 
 */

package mum.pmp.mstore.service.payment;

import mum.pmp.mstore.model.CreditCard;

public interface PaymentService {
	public Boolean confirmPayment(CreditCard credit, String orderNumber, double amount);
}
