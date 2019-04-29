package mum.pmp.mstore.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.repository.OrderRepository;
import mum.pmp.mstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRopository;

	@Override
	public Order getOrderByOrderNumber(Long orderNumber) {
		Optional<Order> result = orderRopository.findById(orderNumber);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}

	@Override
	public List<Order> findBetweenDates(LocalDate fromDate, LocalDate toDate) {
		return orderRopository.findBetweenDates(fromDate, toDate);
	}

}
