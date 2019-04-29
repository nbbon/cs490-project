package mum.pmp.mstore.service;

import java.time.LocalDate;
import java.util.List;

import mum.pmp.mstore.domain.Order;

public interface OrderService {
	public Order getOrderByOrderNumber(Long orderNumber);

	public List<Order> findBetweenDates(LocalDate fromDate, LocalDate toDate);
}
