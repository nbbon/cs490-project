package mum.pmp.mstore.service;

import java.time.LocalDate;
import java.util.List;

import mum.pmp.mstore.domain.Order;

public interface ReportService {
	public List<Order> getWeeklySalesReport(LocalDate fromDate, LocalDate toDate);
}
