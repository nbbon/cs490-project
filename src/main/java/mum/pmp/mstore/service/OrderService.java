
package mum.pmp.mstore.service;

import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.model.Address;


public interface OrderService {
	 public Order createOrder(ShoppingCart shoppingCart);
	 public Order getOrder(String orderNumber);
	 public void setCustomer(String orderNumber, String email) ;
	    
	 void addBillingAddress(String orderNumber,  Address address);
	 void addShippingAddress(String orderNumber, Address address);

}
