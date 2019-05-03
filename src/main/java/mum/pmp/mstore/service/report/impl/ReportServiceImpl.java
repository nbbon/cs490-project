package mum.pmp.mstore.service.report.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.OrderLine;
import mum.pmp.mstore.repository.report.ReportRepository;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.report.AdhocOverallReportData;
import mum.pmp.mstore.service.report.AdhocReportData;
import mum.pmp.mstore.service.report.AnnuallyOverallReportData;
import mum.pmp.mstore.service.report.AnnuallyReportData;
import mum.pmp.mstore.service.report.HalfYearlyOverallReportData;
import mum.pmp.mstore.service.report.HalfYearlyReportData;
import mum.pmp.mstore.service.report.MonthlyOverallReportData;
import mum.pmp.mstore.service.report.MonthlyReportData;
import mum.pmp.mstore.service.report.QuarterlyOverallReportData;
import mum.pmp.mstore.service.report.QuarterlyReportData;
import mum.pmp.mstore.service.report.ReportData;
import mum.pmp.mstore.service.report.ReportService;
import mum.pmp.mstore.service.report.WeeklyOverallReportData;
import mum.pmp.mstore.service.report.WeeklyReportData;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportRepository reportRepository;

	@Override
	public List<WeeklyReportData> getWeeklySalesByProductReport(LocalDate weekDay) {
		LocalDate d1;
		if (weekDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			d1 = weekDay;
		} else {
			d1 = weekDay.minusDays(weekDay.getDayOfWeek().getValue() - 1);
		}
		return reportRepository.weeklySalesByProduct(d1, d1.plusDays(1), d1.plusDays(2), d1.plusDays(3), 
				d1.plusDays(4), d1.plusDays(5), d1.plusDays(6));
	}

	@Override
	public List<WeeklyReportData> getWeeklySalesByCategoryReport(LocalDate weekDay) {
		LocalDate d1;
		if (weekDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			d1 = weekDay;
		} else {
			d1 = weekDay.minusDays(weekDay.getDayOfWeek().getValue() - 1);
		}
		return reportRepository.weeklySalesByCategory(d1, d1.plusDays(1), d1.plusDays(2), d1.plusDays(3), 
				d1.plusDays(4), d1.plusDays(5), d1.plusDays(6));
	}

	@Override
	public List<WeeklyOverallReportData> getWeeklyOverallSalesReport(LocalDate weekDay) {
		LocalDate d1;
		if (weekDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			d1 = weekDay;
		} else {
			d1 = weekDay.minusDays(weekDay.getDayOfWeek().getValue() - 1);
		}
		return reportRepository.weeklyOverallSales(d1, d1.plusDays(1), d1.plusDays(2), d1.plusDays(3), 
				d1.plusDays(4), d1.plusDays(5), d1.plusDays(6));
	}

	@Override
	public List<MonthlyReportData> getMonthlySalesByProductReport(Integer year) {
		return reportRepository.monthlySalesByProduct(year);
	}

	@Override
	public List<MonthlyReportData> getMonthlySalesByCategoryReport(Integer year) {
		return reportRepository.monthlySalesByCategory(year);
	}

	@Override
	public List<MonthlyOverallReportData> getMonthlyOverallSalesReport(Integer year) {
		return reportRepository.monthlyOverallSales(year);
	}

	@Override
	public List<QuarterlyReportData> getQuarterlySalesByProductReport(Integer year) {
		return reportRepository.quarterlySalesByProduct(year);
	}

	@Override
	public List<QuarterlyReportData> getQuarterlySalesByCategoryReport(Integer year) {
		return reportRepository.quarterlySalesByCategory(year);
	}

	@Override
	public List<QuarterlyOverallReportData> getQuarterlyOverallSalesReport(Integer year) {
		return reportRepository.quarterlyOverallSales(year);
	}

	@Override
	public List<HalfYearlyReportData> getHalfYearlySalesByProductReport(Integer year) {
		return reportRepository.halfyearlySalesByProduct(year);
	}

	@Override
	public List<HalfYearlyReportData> getHalfYearlySalesByCategoryReport(Integer year) {
		return reportRepository.halfyearlySalesByCategory(year);
	}

	@Override
	public List<HalfYearlyOverallReportData> getHalfYearlyOverallSalesReport(Integer year) {
		return reportRepository.halfyearlyOverallSales(year);
	}

	@Override
	public List<AnnuallyReportData> getAnnuallySalesByProductReport(Integer year) {
		return reportRepository.annuallySalesByProduct(year);
	}

	@Override
	public List<AnnuallyReportData> getAnnuallySalesByCategoryReport(Integer year) {
		return reportRepository.annuallySalesByCategory(year);
	}

	@Override
	public List<AnnuallyOverallReportData> getAnnuallyOverallSalesReport(Integer year) {
		return reportRepository.annuallyOverallSales(year);
	}

	@Override
	public List<AdhocReportData> getAdhocSalesByProductReport(LocalDate startDate, LocalDate endDate) {
		return reportRepository.adhocSalesByProduct(startDate, endDate);
	}

	@Override
	public List<AdhocReportData> getAdhocSalesByCategoryReport(LocalDate startDate, LocalDate endDate) {
		return reportRepository.adhocSalesByCategory(startDate, endDate);
	}

	@Override
	public List<AdhocOverallReportData> getAdhocOverallSalesReport(LocalDate startDate, LocalDate endDate) {
		return reportRepository.adhocOverallSales(startDate, endDate);
	}
}
