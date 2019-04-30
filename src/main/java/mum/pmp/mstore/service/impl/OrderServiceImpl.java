//package mum.pmp.mstore.service.impl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import mum.pmp.mstore.domain.Address;
//import mum.pmp.mstore.domain.Order;
//import mum.pmp.mstore.domain.OrderFactory;
//import mum.pmp.mstore.domain.ShoppingCart;
//import mum.pmp.mstore.model.Customer;
//import mum.pmp.mstore.repository.CustomerRepository;
//import mum.pmp.mstore.repository.OrderRepository;
//import mum.pmp.mstore.service.OrderService;
//
//@Service
//public class OrderServiceImpl implements OrderService{
//	
//	@Autowired
//	OrderRepository orderRepository;
//	
//	@Autowired
//	CustomerRepository customerRepository;	
//
//
//	@Override
//	public Order createOrder(ShoppingCart shoppingCart) {
//
//       Order oder = OrderFactory.createOrder(shoppingCart);
//		return orderRepository.save(oder);
//	}
//
//	@Override
//	public void addCustomer(Long orderNumber, String customerNumber) {
//        Optional<Order>	optional=orderRepository.findById(orderNumber);
//        
//        if (optional.isPresent()) {
//        	Order order = optional.get();
//        	Customer customer= customerRepository.getOne(orderNumber);
//        	
//        	if (customer!=null) {
//				order.setCustomer(customer);
//			}
//        	
//        	orderRepository.save(order);
//			
//		}
//	}
//
//	@Override
//	public void addBillingAddress(Long orderNumber, String country, String state, String city, String zip,
//			String street) {
//          Optional<Order>	optional=orderRepository.findById(orderNumber);
//          if (optional.isPresent()) {
//			Order order=optional.get();
//			
//			Address address= new Address(country,  state,  city,  zip,street);
//			order.setBillingAddress(address);
//		}
//		
//	}
//
//	@Override
//	public void addShippingAddress(Long orderNumber, String country, String state, String city, String zip,
//			String street) {
//		 Optional<Order>	optional=orderRepository.findById(orderNumber);
//         if (optional.isPresent()) {
//			Order order=optional.get();
//			
//			Address address= new Address(country,  state,  city,  zip,street);
//			order.setBillingAddress(address);
//		}
//				
//	}
//	
//	
//}
