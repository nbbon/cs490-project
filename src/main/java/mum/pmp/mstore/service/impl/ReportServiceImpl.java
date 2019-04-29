package mum.pmp.mstore.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderLine;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	OrderService orderService;

	@Override
	public List<Order> getWeeklySalesReport(LocalDate fromDate, LocalDate toDate) {
		return orderService.findBetweenDates(fromDate, toDate);
//		List<Order> orders = orderService.findBetweenDates(fromDate, toDate);
//		Map<LocalDate, Map<String, Integer>> reports = new TreeMap<>();
//		for (Order order : orders) {
//			LocalDate saleDate = order.getDate();
//			List<OrderLine> orderLines = order.getOrderLines();
//			for (OrderLine orderLine : orderLines) {
//				Map<String, Integer> sales = reports.containsKey(saleDate) ? reports.get(saleDate)
//						: new TreeMap<>();
//				
//				String productNumber = orderLine.getProduct().getProductName();
//				int quantity = sales.containsKey(productNumber) ? sales.get(productNumber) + orderLine.getQuantity()
//						: orderLine.getQuantity();
//				sales.put(orderLine.getProduct().getProductName(), quantity);
//
//				if(!reports.containsKey(saleDate)) reports.put(saleDate, sales);
//			}
//		}
	}

}
