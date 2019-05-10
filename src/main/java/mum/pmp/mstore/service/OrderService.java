/*
 * Author: Jean Chrisner Jean Charles
 * Date: 29-Apr-2019
 * Class Name: OrderService
 * Package: service
 * Description: contains the business logic of order module to operate on the data sent to and from the DAO and the client.
 * 
 */

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
	public Order save(Order order);
	public void remove(String orderNumber);

}
