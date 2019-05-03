package mum.pmp.mstore.service.report.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.service.report.AdhocOverallReportData;
import mum.pmp.mstore.service.report.AdhocReportData;
import mum.pmp.mstore.service.report.AnnuallyOverallReportData;
import mum.pmp.mstore.service.report.AnnuallyReportData;
import mum.pmp.mstore.service.report.HalfYearlyOverallReportData;
import mum.pmp.mstore.service.report.HalfYearlyReportData;
import mum.pmp.mstore.service.report.MonthlyReportData;
import mum.pmp.mstore.service.report.QuarterlyOverallReportData;
import mum.pmp.mstore.service.report.QuarterlyReportData;
import mum.pmp.mstore.service.report.ReportBuilder;
import mum.pmp.mstore.service.report.ReportService;
import mum.pmp.mstore.service.report.WeeklyReportData;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Component
public class ReportBuilderImpl implements ReportBuilder {

	@Autowired
	ReportService reportService;

	@Override
	public JasperPrint buildWeeklySalesReport(LocalDate week, ReportType type) throws IOException, JRException {
		if(type == ReportType.BY_PRODUCT) {
			return buildWeeklySalesByProductReport(week);
		}
		if(type == ReportType.BY_CATEGORY) {
			return buildWeeklySalesByCategoryReport(week);
		}
		if(type == ReportType.OVERALL) {
			return buildWeeklyOverallSalesReport(week);
		}
		return null;
	}
	
	@Override
	public JasperPrint buildMonthlySalesReport(Integer year, ReportType type) throws IOException, JRException {
		if(type == ReportType.BY_PRODUCT) {
			return buildMonthlySalesByProductReport(year);
		}
		if(type == ReportType.BY_CATEGORY) {
			return buildMonthlySalesByCategoryReport(year);
		}
		if(type == ReportType.OVERALL) {
			return buildMonthlyOverallSalesReport(year);
		}
		return null;
	}

	@Override
	public JasperPrint buildHalfYearlySalesReport(Integer year, ReportType type) throws IOException, JRException {
		if(type == ReportType.BY_PRODUCT) {
			return buildHalfYearlySalesByProductReport(year);
		}
		if(type == ReportType.BY_CATEGORY) {
			return buildHalfYearlySalesByCategoryReport(year);
		}
		if(type == ReportType.OVERALL) {
			return buildHalfYearlyOverallSalesReport(year);
		}
		return null;
	}
	
	@Override
	public JasperPrint buildQuarterlySalesReport(Integer year, ReportType type) throws IOException, JRException {
		if(type == ReportType.BY_PRODUCT) {
			return buildQuarterlySalesByProductReport(year);
		}
		if(type == ReportType.BY_CATEGORY) {
			return buildQuarterlySalesByCategoryReport(year);
		}
		if(type == ReportType.OVERALL) {
			return buildQuarterlyOverallSalesReport(year);
		}
		return null;
	}

	@Override
	public JasperPrint buildAnnuallySalesReport(Integer year, ReportType type) throws IOException, JRException {
		if(type == ReportType.BY_PRODUCT) {
			return builddAnnuallySalesByProductReport(year);
		}
		if(type == ReportType.BY_CATEGORY) {
			return builddAnnuallySalesByCategoryReport(year);
		}
		if(type == ReportType.OVERALL) {
			return builddAnnuallyOverallSalesReport(year);
		}
		return null;
	}

	@Override
	public JasperPrint buildAdhocSalesReport(LocalDate fromDate, LocalDate toDate, ReportType type) throws IOException, JRException {
		if(type == ReportType.BY_PRODUCT) {
			return buildAdhocSalesByProductReport(fromDate, toDate);
		}
		if(type == ReportType.BY_CATEGORY) {
			return buildAdhocSalesByCategoryReport(fromDate, toDate);
		}
		if(type == ReportType.OVERALL) {
			return buildAdhocOverallSalesReport(fromDate, toDate);
		}
		return null;
	}
	

	private JasperPrint buildMonthlyOverallSalesReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildMonthlyJPReport(ReportType.OVERALL);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportService.getMonthlyOverallSalesReport(year));

		Map<String, Object> parameters = buildMonthlyOverallSalesReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildMonthlySalesByCategoryReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildMonthlyJPReport(ReportType.BY_CATEGORY);
		List<MonthlyReportData> dataList = reportService.getMonthlySalesByCategoryReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildMonthlySalesByCategoryReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildMonthlySalesByProductReport(Integer year) throws IOException, JRException {
		
		JasperReport jasperReport = buildMonthlyJPReport(ReportType.BY_PRODUCT);
		List<MonthlyReportData> dataList = reportService.getMonthlySalesByProductReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildMonthlySalesByProductReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildQuarterlySalesByProductReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildQuarterlyJPReport(ReportType.BY_PRODUCT);
		List<QuarterlyReportData> dataList = reportService.getQuarterlySalesByProductReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildQuarterlySalesByProductReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildQuarterlySalesByCategoryReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildQuarterlyJPReport(ReportType.BY_CATEGORY);
		List<QuarterlyReportData> dataList = reportService.getQuarterlySalesByCategoryReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildQuarterlySalesByCategoryReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildQuarterlyOverallSalesReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildQuarterlyJPReport(ReportType.OVERALL);
		List<QuarterlyOverallReportData> dataList = reportService.getQuarterlyOverallSalesReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildQuarterlyOverallSalesReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildHalfYearlyOverallSalesReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildHalfYearlyJPReport(ReportType.OVERALL);
		List<HalfYearlyOverallReportData> dataList = reportService.getHalfYearlyOverallSalesReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildHalfYearlyOverallSalesReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildHalfYearlySalesByCategoryReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildHalfYearlyJPReport(ReportType.BY_CATEGORY);
		List<HalfYearlyReportData> dataList = reportService.getHalfYearlySalesByCategoryReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildHalfYearlySalesByCategoryReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildHalfYearlySalesByProductReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildHalfYearlyJPReport(ReportType.BY_PRODUCT);
		List<HalfYearlyReportData> dataList = reportService.getHalfYearlySalesByProductReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildHalfYearlySalesByProductReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint builddAnnuallyOverallSalesReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildAnnuallyJPReport(ReportType.OVERALL);
		List<AnnuallyOverallReportData> dataList = reportService.getAnnuallyOverallSalesReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildAnnuallyOverallSalesReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint builddAnnuallySalesByCategoryReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildAnnuallyJPReport(ReportType.BY_CATEGORY);
		List<AnnuallyReportData> dataList = reportService.getAnnuallySalesByCategoryReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildAnnuallySalesByCategoryReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint builddAnnuallySalesByProductReport(Integer year) throws IOException, JRException {
		JasperReport jasperReport = buildAnnuallyJPReport(ReportType.BY_PRODUCT);
		List<AnnuallyReportData> dataList = reportService.getAnnuallySalesByProductReport(year);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildAnnuallySalesByProductReportParameters(year);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildAdhocSalesByProductReport(LocalDate fromDate, LocalDate toDate) throws IOException, JRException {
		JasperReport jasperReport = buildAdhocJPReport(ReportType.BY_PRODUCT);
		List<AdhocReportData> dataList = reportService.getAdhocSalesByProductReport(fromDate, toDate);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildAdhocSalesByProductReportParameters(fromDate, toDate);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildAdhocSalesByCategoryReport(LocalDate fromDate, LocalDate toDate) throws IOException, JRException {
		JasperReport jasperReport = buildAdhocJPReport(ReportType.BY_CATEGORY);
		List<AdhocReportData> dataList = reportService.getAdhocSalesByProductReport(fromDate, toDate);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildAdhocSalesByCategoryReportParameters(fromDate, toDate);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildAdhocOverallSalesReport(LocalDate fromDate, LocalDate toDate) throws IOException, JRException {	
		List<AdhocOverallReportData> dataList = reportService.getAdhocOverallSalesReport(fromDate, toDate);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		
		Map<String, Object> parameters = buildAdhocOverallSalesReportParameters(fromDate, toDate);
		
		JasperReport jasperReport = buildAdhocJPReport(ReportType.OVERALL);
		
		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	private JasperPrint buildWeeklySalesByProductReport(LocalDate week) throws IOException, JRException {
		LocalDate d1;
		if (week.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			d1 = week;
		} else {
			d1 = week.minusDays(week.getDayOfWeek().getValue() - 1);
		}
		
		JasperReport jasperReport = buildWeeklyJPReport(ReportType.BY_PRODUCT);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportService.getWeeklySalesByProductReport(d1));

		Map<String, Object> parameters = buildWeeklySalesByProductReportParameters(d1);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}
	
	private JasperPrint buildWeeklySalesByCategoryReport(LocalDate week) throws IOException, JRException {
		LocalDate d1;
		if (week.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			d1 = week;
		} else {
			d1 = week.minusDays(week.getDayOfWeek().getValue() - 1);
		}
		
		JasperReport jasperReport = buildWeeklyJPReport(ReportType.BY_CATEGORY);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportService.getWeeklySalesByCategoryReport(d1));

		Map<String, Object> parameters = buildWeeklySalesByCategoryReportParameters(d1);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}
	
	private JasperPrint buildWeeklyOverallSalesReport(LocalDate week) throws IOException, JRException {
		LocalDate d1;
		if (week.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			d1 = week;
		} else {
			d1 = week.minusDays(week.getDayOfWeek().getValue() - 1);
		}
		
		JasperReport jasperReport = buildWeeklyJPReport(ReportType.OVERALL);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportService.getWeeklyOverallSalesReport(d1));

		Map<String, Object> parameters = buildWeeklyOverallSalesReportParameters(d1);

		return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}
	
	private Map<String, Object> buildWeeklySalesByProductReportParameters(LocalDate weekStartDay) {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Weekly Sales By Product Report");
		parameters.put("period", "Week period:");
		parameters.put("startDate", weekStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", weekStartDay.plusDays(6).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Product Number");
		parameters.put("itemName", "Product Name");
		parameters.put("d1", weekStartDay.format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d2", weekStartDay.plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d3", weekStartDay.plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d4", weekStartDay.plusDays(3).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d5", weekStartDay.plusDays(4).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d6", weekStartDay.plusDays(5).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d7", weekStartDay.plusDays(6).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildWeeklySalesByCategoryReportParameters(LocalDate weekStartDay) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Weekly Sales By Product Category Report");
		parameters.put("period", "Week period:");
		parameters.put("startDate", weekStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", weekStartDay.plusDays(6).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Category ID");
		parameters.put("itemName", "Category Name");
		parameters.put("d1", weekStartDay.format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d2", weekStartDay.plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d3", weekStartDay.plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d4", weekStartDay.plusDays(3).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d5", weekStartDay.plusDays(4).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d6", weekStartDay.plusDays(5).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d7", weekStartDay.plusDays(6).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildWeeklyOverallSalesReportParameters(LocalDate weekStartDay) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Weekly Overall Sales Report");
		parameters.put("period", "Week period:");
		parameters.put("startDate", weekStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", weekStartDay.plusDays(6).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("d1", weekStartDay.format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d2", weekStartDay.plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d3", weekStartDay.plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d4", weekStartDay.plusDays(3).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d5", weekStartDay.plusDays(4).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d6", weekStartDay.plusDays(5).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("d7", weekStartDay.plusDays(6).format(DateTimeFormatter.ofPattern("MM/dd")));
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildMonthlySalesByProductReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Monthly Sales By Product Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Product Number");
		parameters.put("itemName", "Product Name");
		parameters.put("jan", "JAN");
		parameters.put("feb", "FEB");
		parameters.put("mar", "MAR");
		parameters.put("apr", "APR");
		parameters.put("may", "MAY");
		parameters.put("jun", "JUN");
		parameters.put("jul", "JUL");
		parameters.put("aug", "AUG");
		parameters.put("sep", "SEP");
		parameters.put("oct", "OCT");
		parameters.put("nov", "NOV");
		parameters.put("dec", "DEC");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildMonthlySalesByCategoryReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Monthly Sales By Product Category Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Category Number");
		parameters.put("itemName", "Category Name");
		parameters.put("jan", "JAN");
		parameters.put("feb", "FEB");
		parameters.put("mar", "MAR");
		parameters.put("apr", "APR");
		parameters.put("may", "MAY");
		parameters.put("jun", "JUN");
		parameters.put("jul", "JUL");
		parameters.put("aug", "AUG");
		parameters.put("sep", "SEP");
		parameters.put("oct", "OCT");
		parameters.put("nov", "NOV");
		parameters.put("dec", "DEC");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildMonthlyOverallSalesReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Monthly Overall Sales Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("jan", "JAN");
		parameters.put("feb", "FEB");
		parameters.put("mar", "MAR");
		parameters.put("apr", "APR");
		parameters.put("may", "MAY");
		parameters.put("jun", "JUN");
		parameters.put("jul", "JUL");
		parameters.put("aug", "AUG");
		parameters.put("sep", "SEP");
		parameters.put("oct", "OCT");
		parameters.put("nov", "NOV");
		parameters.put("dec", "DEC");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildQuarterlySalesByProductReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Quarterly Sales By Product Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Product Number");
		parameters.put("itemName", "Product Name");
		parameters.put("q1", "Q1");
		parameters.put("q2", "Q2");
		parameters.put("q3", "Q3");
		parameters.put("q4", "Q4");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildQuarterlySalesByCategoryReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Quarterly Sales By Product Category Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Category Number");
		parameters.put("itemName", "Category Name");
		parameters.put("q1", "Q1");
		parameters.put("q2", "Q2");
		parameters.put("q3", "Q3");
		parameters.put("q4", "Q4");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildQuarterlyOverallSalesReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Quarterly Overall Sales Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("q1", "Q1");
		parameters.put("q2", "Q2");
		parameters.put("q3", "Q3");
		parameters.put("q4", "Q4");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildHalfYearlySalesByProductReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Half-Yearly Sales By Product Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Product Number");
		parameters.put("itemName", "Product Name");
		parameters.put("fh", "Frist half");
		parameters.put("sh", "Second half");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildHalfYearlySalesByCategoryReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Half-yearly Sales By Product Category Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Category Number");
		parameters.put("itemName", "Category Name");
		parameters.put("fh", "Frist half");
		parameters.put("sh", "Second half");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildHalfYearlyOverallSalesReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Half-Yearly Overall Sales Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("fh", "Frist half");
		parameters.put("sh", "Second half");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}

	private Map<String, Object> buildAnnuallySalesByProductReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Annually Sales By Product Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Product Number");
		parameters.put("itemName", "Product Name");
		parameters.put("year", "Year");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildAnnuallySalesByCategoryReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Annually Sales By Product Category Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Category Number");
		parameters.put("itemName", "Category Name");
		parameters.put("year", "Year");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildAnnuallyOverallSalesReportParameters(Integer year) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Annually Overall Sales Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", LocalDate.of(year, 1, 1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", LocalDate.of(year, 12, 31).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("year", "Year");
		parameters.put("totalSales", "Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildAdhocSalesByProductReportParameters(LocalDate startDate, LocalDate endDate) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Sales By Product Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Product Number");
		parameters.put("itemName", "Product Name");
		parameters.put("rangeSales", "Sale Date");
		parameters.put("totalSales", "Total Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildAdhocSalesByCategoryReportParameters(LocalDate startDate, LocalDate endDate) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Sales By Product Category Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("itemId", "Category Number");
		parameters.put("itemName", "Category Name");
		parameters.put("rangeSales", "Sale Date");
		parameters.put("totalSales", "Total Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private Map<String, Object> buildAdhocOverallSalesReportParameters(LocalDate startDate, LocalDate endDate) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Overall Sales Report");
		parameters.put("period", "Period:");
		parameters.put("startDate", startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("endDate", endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		parameters.put("rangeSales", "Sale Date");
		parameters.put("totalSales", "Total Sales");
		parameters.put("total", "Total ($)");
		
		return parameters;
	}
	
	private JasperReport buildWeeklyJPReport(ReportType type) throws IOException, JRException {
		return JasperCompileManager.compileReport(getJPReportWeeklyTemplate(type));
	}
	
	private InputStream getJPReportWeeklyTemplate(ReportType type) throws IOException {
		if(type == ReportType.OVERALL) {
			return (new ClassPathResource("jrxml/weeklyOverallSalesReport.jrxml")).getInputStream();
		} else {
			return (new ClassPathResource("jrxml/weeklySalesReport.jrxml")).getInputStream();
		}
	}
	
	private JasperReport buildMonthlyJPReport(ReportType type) throws IOException, JRException {
		return JasperCompileManager.compileReport(getMonthlyJPReportTemplate(type));
	}
	
	private InputStream getMonthlyJPReportTemplate(ReportType type) throws IOException {
		if(type == ReportType.OVERALL) {
			return (new ClassPathResource("jrxml/monthlyOverallSalesReport.jrxml")).getInputStream();
		} else {
			return (new ClassPathResource("jrxml/monthlySalesReport.jrxml")).getInputStream();
		}
	}
	
	private JasperReport buildQuarterlyJPReport(ReportType type) throws IOException, JRException {
		return JasperCompileManager.compileReport(getQuarterlyJPReportTemplate(type));
	}
	
	private InputStream getQuarterlyJPReportTemplate(ReportType type) throws IOException {
		if(type == ReportType.OVERALL) {
			return (new ClassPathResource("jrxml/quarterlyOverallSalesReport.jrxml")).getInputStream();
		} else {
			return (new ClassPathResource("jrxml/quarterlySalesReport.jrxml")).getInputStream();
		}
	}
	
	private JasperReport buildHalfYearlyJPReport(ReportType type) throws IOException, JRException {
		return JasperCompileManager.compileReport(getHalfYearlyJPReportTemplate(type));
	}
	
	private InputStream getHalfYearlyJPReportTemplate(ReportType type) throws IOException {
		if(type == ReportType.OVERALL) {
			return (new ClassPathResource("jrxml/halfYearlyOverallSalesReport.jrxml")).getInputStream();
		} else {
			return (new ClassPathResource("jrxml/halfYearlySalesReport.jrxml")).getInputStream();
		}
	}
	
	private JasperReport buildAnnuallyJPReport(ReportType type) throws IOException, JRException {
		return JasperCompileManager.compileReport(getAnnuallyJPReportTemplate(type));
	}
	
	private InputStream getAnnuallyJPReportTemplate(ReportType type) throws IOException {
		if(type == ReportType.OVERALL) {
			return (new ClassPathResource("jrxml/annuallyOverallSalesReport.jrxml")).getInputStream();
		} else {
			return (new ClassPathResource("jrxml/annuallySalesReport.jrxml")).getInputStream();
		}
	}
	
	private JasperReport buildAdhocJPReport(ReportType type) throws IOException, JRException {
		return JasperCompileManager.compileReport(getAdhocJPReportTemplate(type));
	}
	
	private InputStream getAdhocJPReportTemplate(ReportType type) throws IOException {
		if(type == ReportType.OVERALL) {
			return (new ClassPathResource("jrxml/adhocOverallSalesReport.jrxml")).getInputStream();
		} else {
			return (new ClassPathResource("jrxml/adhocSalesReport.jrxml")).getInputStream();
		}
	}
}
