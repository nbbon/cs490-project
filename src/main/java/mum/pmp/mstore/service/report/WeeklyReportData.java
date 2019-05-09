/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: WeeklyReportData
 * Module: Report
 * Description: The report data holder class, store data for weekly product and product category report 
 * Used by ReportRepository to fill data query from DB
 * 
 */

package mum.pmp.mstore.service.report;

public class WeeklyReportData {
	private String itemId;
	private String itemName;
	private Integer d1Sales;
	private Integer d2Sales;
	private Integer d3Sales;
	private Integer d4Sales;
	private Integer d5Sales;
	private Integer d6Sales;
	private Integer d7Sales;
	private Integer totalSales;
	private Double total;
	
	
	
	public WeeklyReportData(String itemId, String itemName, Integer d1Sales, Integer d2Sales, Integer d3Sales,
			Integer d4Sales, Integer d5Sales, Integer d6Sales, Integer d7Sales, Integer totalSales, Double total) {

		this.itemId = itemId;
		this.itemName = itemName;
		this.d1Sales = d1Sales;
		this.d2Sales = d2Sales;
		this.d3Sales = d3Sales;
		this.d4Sales = d4Sales;
		this.d5Sales = d5Sales;
		this.d6Sales = d6Sales;
		this.d7Sales = d7Sales;
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

	public Integer getD1Sales() {
		return d1Sales;
	}
	public void setD1Sales(Integer d1Sales) {
		this.d1Sales = d1Sales;
	}
	public Integer getD2Sales() {
		return d2Sales;
	}
	public void setD2Sales(Integer d2Sales) {
		this.d2Sales = d2Sales;
	}
	public Integer getD3Sales() {
		return d3Sales;
	}
	public void setD3Sales(Integer d3Sales) {
		this.d3Sales = d3Sales;
	}
	public Integer getD4Sales() {
		return d4Sales;
	}
	public void setD4Sales(Integer d4Sales) {
		this.d4Sales = d4Sales;
	}
	public Integer getD5Sales() {
		return d5Sales;
	}
	public void setD5Sales(Integer d5Sales) {
		this.d5Sales = d5Sales;
	}
	public Integer getD6Sales() {
		return d6Sales;
	}
	public void setD6Sales(Integer d6Sales) {
		this.d6Sales = d6Sales;
	}
	public Integer getD7Sales() {
		return d7Sales;
	}
	public void setD7Sales(Integer d7Sales) {
		this.d7Sales = d7Sales;
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
