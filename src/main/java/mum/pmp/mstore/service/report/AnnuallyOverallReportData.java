/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: AnnuallyOverallReportData
 * Module: Report
 * Description: The report data holder class, store data for annually overall report 
 * Used by ReportRepository to fill data query from DB
 * 
 */

package mum.pmp.mstore.service.report;

public class AnnuallyOverallReportData {
	private Integer yearSales;
	private Integer totalSales;
	private Double total;
	public AnnuallyOverallReportData(Integer yearSales, Integer totalSales, Double total) {
		super();
		this.yearSales = yearSales;
		this.totalSales = totalSales;
		this.total = total;
	}
	public Integer getYearSales() {
		return yearSales;
	}
	public void setYearSales(Integer yearSales) {
		this.yearSales = yearSales;
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
