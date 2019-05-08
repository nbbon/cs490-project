/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: HalfYearlyReportData
 * Module: Report
 * Description: The report data holder class, store data for half-yearly product and product category report 
 * Used by ReportRepository to fill data query from DB
 * 
 */


package mum.pmp.mstore.service.report;

public class HalfYearlyReportData {
	private String itemId;
	private String itemName;
	private Integer fhSales;
	private Integer shSales;
	private Integer totalSales;
	private Double total;
	public HalfYearlyReportData(String itemId, String itemName, Integer fhSales, Integer shSales, Integer totalSales,
			Double total) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.fhSales = fhSales;
		this.shSales = shSales;
		this.totalSales = totalSales;
		this.total = total;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getFhSales() {
		return fhSales;
	}
	public void setFhSales(Integer fhSales) {
		this.fhSales = fhSales;
	}
	public Integer getShSales() {
		return shSales;
	}
	public void setShSales(Integer shSales) {
		this.shSales = shSales;
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
