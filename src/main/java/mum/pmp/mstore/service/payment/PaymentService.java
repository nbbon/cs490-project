/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: PaymentService
 * Package: service
 * Description: The payment service interface  
 * 
 */

package mum.pmp.mstore.service.payment;

import mum.pmp.mstore.model.CreditCard;

public interface PaymentService {
	public Boolean confirmPayment(CreditCard credit, String orderNumber, double amount);
}
