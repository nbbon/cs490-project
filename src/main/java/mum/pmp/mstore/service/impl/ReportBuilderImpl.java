package mum.pmp.mstore.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.service.ReportBuilder;
import mum.pmp.mstore.service.ReportService;
import net.sf.jasperreports.engine.JRDataSource;
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
	public String buildWeeklyReport(LocalDate fromDate, LocalDate toDate, ReportType type) throws IOException, JRException {
		System.out.println("Starting report.....................");
		if(type == ReportType.PDF) {
			return buildWeeklyPdfReport(fromDate, toDate);
		}
		
		return "";
	}

	private String buildWeeklyPdfReport(LocalDate fromDate, LocalDate toDate) throws IOException, JRException {
		String outputReport = "weekly-report.pdf";
		InputStream reportStream = new ClassPathResource("weekly-report.jrxml").getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
		
		System.out.println("Building report.....................");
		
		List<Order> orders = reportService.getWeeklySalesReport(fromDate, toDate);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Weekly Sales Report");
		parameters.put("dataSource", orders);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputReport));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("test");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		System.out.println("Exporting report.....................");
		exporter.exportReport();
		
		return "reports/weekly";
	}

}
