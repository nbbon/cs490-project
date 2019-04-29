package mum.pmp.mstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.ReportBuilder;
import mum.pmp.mstore.service.ReportBuilder.ReportType;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Controller
//@RequestMapping("/reports")
public class ReportController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	ReportBuilder reportBuilder;
	
	@GetMapping(value = {"/reports", "/reports/"} )
	public String defautIndexPage(Model model) {
		List<String> periods = new ArrayList<>();
		periods.add("Weekly");
		periods.add("Monthly");
		periods.add("Quarterly");
		periods.add("Half yearly");
		periods.add("Annually");
		periods.add("Ad-hoc");
		
		Map<Integer, String> reportType = new HashMap<>();
		reportType.put(1, "Sales by Product Category");
		reportType.put(2, "Sales by Product");
		reportType.put(3, "Overall sales");
		model.addAttribute("periods", periods);
		model.addAttribute("reportType", reportType);
		
		return "reports/index";
	}
	
//	@PostMapping(value = "/{periodical}/{fromDate}/{toDate}")
//	public String weeklySalesReport(Model model,@PathVariable String periodical, @PathVariable String fromDate, @PathVariable String toDate) {
//		LocalDate ldFromDate = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
//		LocalDate ldToDate = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
//
//		model.addAttribute("fromDate", ldFromDate);
//		model.addAttribute("toDate", ldToDate);
//
//		model.addAttribute("rd", orderService.findBetweenDates(ldFromDate, ldToDate));
//		System.out.println("Here: " + periodical + " " + fromDate + " " + toDate);
//		return "reports/weekly";
//	}

	@PostMapping(value = "/reports/weekly/sales/caterory")
	public void weeklyReports(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletResponse response)  throws Exception{
		LocalDate ldFromDate = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		LocalDate ldToDate = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		System.out.println("Here: " + fromDate + " " + toDate);

		List<String> data = new ArrayList<>();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Weekly Sales Report");
		parameters.put("testString", "Test String");
		
		InputStream inputStream = new ClassPathResource("weekly-report.jrxml").getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		
		response.setContentType("text/html");
		
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}
}
