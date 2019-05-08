/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: AdhocOverallReportData
 * Module: Report
 * Description: The report data holder class, store data for ad-hoc overall report 
 * Used by ReportRepository to fill data query from DB
 * 
 */

package mum.pmp.mstore.service.report;

import java.time.LocalDate;

public class AdhocOverallReportData {
	private LocalDate rangeSales;
	private Integer totalSales;
	private Double total;
	public AdhocOverallReportData(LocalDate rangeSales, Integer totalSales, Double total) {
		super();
		this.rangeSales = rangeSales;
		this.totalSales = totalSales;
		this.total = total;
	}
	public LocalDate getRangeSales() {
		return rangeSales;
	}
	public void setRangeSales(LocalDate rangeSales) {
		this.rangeSales = rangeSales;
	}
	public Integer getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Integer totalSales) {
		this.totalSales = totalSales;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
