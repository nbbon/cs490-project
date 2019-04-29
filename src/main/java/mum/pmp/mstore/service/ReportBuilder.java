package mum.pmp.mstore.service;

import java.io.IOException;
import java.time.LocalDate;

import net.sf.jasperreports.engine.JRException;

public interface ReportBuilder {
	static enum ReportType {PDF, XML, HTML};
	public String buildWeeklyReport(LocalDate fromDate, LocalDate toDate, ReportType type) throws IOException, JRException;
}
