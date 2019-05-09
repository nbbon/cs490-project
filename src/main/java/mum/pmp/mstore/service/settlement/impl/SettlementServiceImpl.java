/*
 * Author: Bon Nguyen
 * Date: 22-Apr-2019
 * Class Name: SettlementService
 * Module: Payment
 * Description: The implementation of SettlementService interface
 * 
 */


package mum.pmp.mstore.service.settlement.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderLine;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.model.Vendor;
import mum.pmp.mstore.service.settlement.SettlementService;

@Service
public class SettlementServiceImpl implements SettlementService {

	@Override
	public Map<String, String> buildInvoice(Order order) {
		Map<String, String> emailList = new HashMap<>();
		String orderNumber = order.getOrderNumber();
		LocalDate orderDate = order.getOrderDate();
		for (OrderLine orderLine : order.getOrderlineList()) {
			// Create invoice for each vendor in this order
			Product product = orderLine.getProduct();
			Vendor vendor = product.getVendor();
			if(vendor != null) {
				String vNumber = vendor.getVendorNumber(); 
				String vEmail = vendor.getEmail(); 
				emailList.put(vNumber, product.getVendor().getEmail());
				System.out.println("Vendor: " + vNumber + " - " + vEmail);
			}
		}

		emailList.put(order.getCustomer().getFirstName() + " " + order.getCustomer().getFirstName(),
				order.getCustomer().getEmail());
		return emailList;
	}

}
