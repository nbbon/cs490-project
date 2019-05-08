/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: ReportController
 * Module: Report
 * Description: The controller of Report module, responsible for handling report requests from UI (HTML)
 * When receive generate report request from UI. check input data and then passes the request 
 * to the ReportBuilder for generating report based on the input data. Send the generated reports to UI 
 * for display 
 */

package mum.pmp.mstore.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.pmp.mstore.service.report.ReportBuilder;
import mum.pmp.mstore.service.report.ReportBuilder.ReportType;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Controller
@RequestMapping("/reports")
public class ReportController {
	@Autowired
	ReportBuilder reportBuilder;
	
	@GetMapping(value = {"", "/"} )
	public String defautIndexPage(Model model) {
		List<String> periods = new ArrayList<>();
		periods.add("Weekly");
		periods.add("Monthly");
		periods.add("Quarterly");
		periods.add("Half yearly");
		periods.add("Annually");
		periods.add("Ad-hoc");
		
		Map<Integer, String> reportType = new HashMap<>();
		reportType.put(1, "Sales by Product");
		reportType.put(2, "Sales by Product Category");
		reportType.put(3, "Overall sales");
		
		List<Integer> years = new ArrayList<>();
		int y = LocalDate.now().getYear();
		for(int i=0; i<5; ++i ) {
			years.add(y - i);
		}
		
		model.addAttribute("periods", periods);
		model.addAttribute("reportType", reportType);
		model.addAttribute("years", years);
		
		return "reports/index";
	}
	    	
	@PostMapping("weekly")
	public String weeklyReport(Model model, RedirectAttributes redirectAttributes, @RequestParam String weekDay, @RequestParam String type) {
		redirectAttributes.addFlashAttribute("weekDay", weekDay);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/weekly";
	}
	
	@PostMapping("weekly/{export}")
	public String exportWeeklyReport(Model model, RedirectAttributes redirectAttributes, @RequestParam String weekDay, @RequestParam String type, @PathVariable Integer export) {
		redirectAttributes.addFlashAttribute("weekDay", weekDay);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/weekly/" + export;
	}
	
	@GetMapping("weekly/{export}")
	public void saveFile(@ModelAttribute("weekDay") String weekDay, @ModelAttribute("type") String type, @PathVariable Integer export, Model model, HttpServletResponse response) throws IOException {
		try {
			
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.setContentType("text/html");
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			LocalDate ldWeekDate = LocalDate.parse(weekDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			JasperPrint jasperPrint = reportBuilder.buildWeeklySalesReport(ldWeekDate, reportType);
			if(jasperPrint == null) {
				response.setContentType("text/html");
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			String filename = "weekly_report.pdf";
//			JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment: filename=" + filename);
			response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Expires", "0");
			if(export == 1) {
				JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			} else {
				JasperExportManager.exportReportToXmlStream(jasperPrint, response.getOutputStream());
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}		
	}
	
	@PostMapping("monthly")
	public String monthlyReport(Model model, RedirectAttributes redirectAttributes, @RequestParam Integer year, @RequestParam String type) {
		redirectAttributes.addFlashAttribute("year", year);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/monthly";
	}
	
	@PostMapping("quarterly")
	public String quarterlyReport(Model model, RedirectAttributes redirectAttributes, @RequestParam Integer year, @RequestParam String type) {
		redirectAttributes.addFlashAttribute("year", year);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/quarterly";
	}
	
	@PostMapping("halfyearly")
	public String halfyearlyReport(Model model, RedirectAttributes redirectAttributes, @RequestParam Integer year, @RequestParam String type) {
		redirectAttributes.addFlashAttribute("year", year);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/halfyearly";
	}
	
	@PostMapping("annually")
	public String annuallyReport(Model model, RedirectAttributes redirectAttributes, @RequestParam Integer year, @RequestParam String type) {
		redirectAttributes.addFlashAttribute("year", year);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/annually";
	}
	
	@PostMapping("adhoc")
	public String adhocReport(Model model, RedirectAttributes redirectAttributes, @RequestParam String adhocFromDate, @RequestParam String adhocToDate, @RequestParam String type) {
		redirectAttributes.addFlashAttribute("adhocFromDate", adhocFromDate);
		redirectAttributes.addFlashAttribute("adhocToDate", adhocToDate);
		redirectAttributes.addFlashAttribute("type", type);
		return "redirect:/reports/adhoc";
	}

	@GetMapping(value = "weekly")
	public void getWeeklyReport(@ModelAttribute("weekDay") String weekDay, @ModelAttribute("type") String type, Model model, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			if(weekDay.isEmpty() || type.isEmpty()) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			LocalDate ldWeekDate = LocalDate.parse(weekDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			JasperPrint jasperPrint = reportBuilder.buildWeeklySalesReport(ldWeekDate, reportType);
			if(jasperPrint == null) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			exportReport(jasperPrint, response.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	@GetMapping("monthly")
	public void getMonthlyReport(@ModelAttribute("year") Integer year, @ModelAttribute("type") String type, Model model, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			if(type.isEmpty()) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			JasperPrint jasperPrint = reportBuilder.buildMonthlySalesReport(year, reportType);
			if(jasperPrint == null) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			exportReport(jasperPrint, response.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	@GetMapping("quarterly")
	public void getQuarterlyReport(@ModelAttribute("year") Integer year, @ModelAttribute("type") String type, Model model, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			
			if(type.isEmpty()) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			JasperPrint jasperPrint = reportBuilder.buildQuarterlySalesReport(year, reportType);
			if(jasperPrint == null) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			exportReport(jasperPrint, response.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	@GetMapping("halfyearly")
	public void getHalfyearlyReport(@ModelAttribute("year") Integer year, @ModelAttribute("type") String type, Model model, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			
			if(type.isEmpty()) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			JasperPrint jasperPrint = reportBuilder.buildHalfYearlySalesReport(year, reportType);
			if(jasperPrint == null) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			exportReport(jasperPrint, response.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	@GetMapping("annually")
	public void getAnnuallyReport(@ModelAttribute("year") Integer year, @ModelAttribute("type") String type, Model model, HttpServletResponse response) {
		try {
			response.setContentType("text/html");		
			if(type.isEmpty()) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			JasperPrint jasperPrint = reportBuilder.buildAnnuallySalesReport(year, reportType);
			if(jasperPrint == null) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			exportReport(jasperPrint, response.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	@GetMapping("adhoc")
	public void getAdhocReport(@ModelAttribute("adhocFromDate") String adhocFromDate, @ModelAttribute("adhocToDate") String adhocToDate, @ModelAttribute("type") String type, Model model, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			
			if(type.isEmpty()) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			ReportType reportType = getReportType(type);
			if(reportType == null) {
				response.getWriter().println("Unable to generate report " + type);
				return;
			}
			
			LocalDate fromDate = LocalDate.parse(adhocFromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate toDate = LocalDate.parse(adhocToDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			JasperPrint jasperPrint = reportBuilder.buildAdhocSalesReport(fromDate, toDate, reportType);
			if(jasperPrint == null) {
				response.getWriter().println("Unable to generate report!");
				return;
			}
			
			exportReport(jasperPrint, response.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (JRException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	private ReportType getReportType(String type) {
		if(type.equalsIgnoreCase("product")) {
			return ReportType.BY_PRODUCT;
		} else if(type.equalsIgnoreCase("category")) {
			return ReportType.BY_CATEGORY;
		} else if(type.equalsIgnoreCase("overall")) {
			return ReportType.OVERALL;
		}
		
		return null;
	}
	
	private void exportReport(JasperPrint jasperPrint, OutputStream output) throws JRException {
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(output));
		exporter.exportReport();
	}
	
	private void saveAsPDF(JasperPrint jasperPrint, OutputStream output) throws JRException {
		JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("CS490 Course - Team2");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		
		exporter.exportReport();
	}

}
