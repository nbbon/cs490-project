package mum.pmp.mstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderFactory;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.model.Address;
import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.repository.AddressRepository;
import mum.pmp.mstore.repository.OrderRepository;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.security.ProfileService;

public class OrderServiceImpl implements OrderService{
	@Autowired
	ProfileService profileService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	AddressRepository addressRepository;
	

	@Override
	public void createOrder(ShoppingCart shoppingCart) {

		Order order = OrderFactory.createOrder(shoppingCart);
		orderRepository.save(order);	
		
	}


	@Override
	public Order getOrder(String orderNumber) {
		return orderRepository.findById(orderNumber).orElse(null);
	}


	@Override
	public void setCustomer(String orderNumber, String email) {
			
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
            
            Customer customer = (Customer)profileService.findByEmail(email);       
            if (customer != null) {
            	
                order.setCustomer(customer);
            }
            orderRepository.save(order);
        }	
        	
	}


	@Override
	public void addBillingAddress(String orderNumber,Address address) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
            addressRepository.save(address);
            order.setBillingAddress(address);
            orderRepository.save(order);
        }	
        		
	}


	@Override
	public void addShoppingAddress(String orderNumber,Address address) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
            addressRepository.save(address);
            order.setBillingAddress(address); 
            orderRepository.save(order);
        }			
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
