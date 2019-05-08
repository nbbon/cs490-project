/*
 * Author: Bon Nguyen
 * Date: 22-Apr-2019
 * Class Name: SettlementService
 * Module: Payment
 * Description: The settlement service interface, defined necessary methods fulfilling the order after 
 * confirmed payment.  
 * 
 */

package mum.pmp.mstore.service.settlement;

import java.util.Map;

import mum.pmp.mstore.domain.Order;

public interface SettlementService {

	Map<String, String> buildInvoice(Order order);

}
