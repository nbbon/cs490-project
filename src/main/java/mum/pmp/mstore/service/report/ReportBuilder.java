package mum.pmp.mstore.service.report;

import java.io.IOException;
import java.time.LocalDate;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportBuilder {
	static enum ReportType {
		BY_PRODUCT, BY_CATEGORY, OVERALL
	};

	public JasperPrint buildWeeklySalesReport(LocalDate week, ReportType type) throws IOException, JRException;

	public JasperPrint buildMonthlySalesReport(Integer year, ReportType type) throws IOException, JRException;

	public JasperPrint buildQuarterlySalesReport(Integer year, ReportType reportType) throws IOException, JRException;

	public JasperPrint buildHalfYearlySalesReport(Integer year, ReportType reportType) throws IOException, JRException;

	public JasperPrint buildAnnuallySalesReport(Integer year, ReportType reportType) throws IOException, JRException;

	public JasperPrint buildAdhocSalesReport(LocalDate fromDate, LocalDate toDate, ReportType reportType)
			throws IOException, JRException;
}
