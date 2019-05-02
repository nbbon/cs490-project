package mum.pmp.mstore.service;

import java.time.LocalDate;
import java.util.List;

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
import mum.pmp.mstore.service.report.WeeklyOverallReportData;
import mum.pmp.mstore.service.report.WeeklyReportData;

public interface ReportService {
	public List<WeeklyReportData> getWeeklySalesByProductReport(LocalDate weekDay);
	public List<WeeklyReportData> getWeeklySalesByCategoryReport(LocalDate weekDay);
	public List<WeeklyOverallReportData> getWeeklyOverallSalesReport(LocalDate weekDay);
	
	public List<MonthlyReportData> getMonthlySalesByProductReport(Integer year);
	public List<MonthlyReportData> getMonthlySalesByCategoryReport(Integer year);
	public List<MonthlyOverallReportData> getMonthlyOverallSalesReport(Integer year);
	
	public List<QuarterlyReportData> getQuarterlySalesByProductReport(Integer year);
	public List<QuarterlyReportData> getQuarterlySalesByCategoryReport(Integer year);
	public List<QuarterlyOverallReportData> getQuarterlyOverallSalesReport(Integer year);
	
	public List<HalfYearlyReportData> getHalfYearlySalesByProductReport(Integer year);
	public List<HalfYearlyReportData> getHalfYearlySalesByCategoryReport(Integer year);
	public List<HalfYearlyOverallReportData> getHalfYearlyOverallSalesReport(Integer year);
	
	public List<AnnuallyReportData> getAnnuallySalesByProductReport(Integer year);
	public List<AnnuallyReportData> getAnnuallySalesByCategoryReport(Integer year);
	public List<AnnuallyOverallReportData> getAnnuallyOverallSalesReport(Integer year);
	
	public List<AdhocReportData> getAdhocSalesByProductReport(LocalDate startDate, LocalDate endDate);
	public List<AdhocReportData> getAdhocSalesByCategoryReport(LocalDate startDate, LocalDate endDate);
	public List<AdhocOverallReportData> getAdhocOverallSalesReport(LocalDate startDate, LocalDate endDate);
}
