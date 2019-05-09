/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: QuarterlyReportData
 * Module: Report
 * Description: The report data holder class, store data for quarterly product and product category report 
 * Used by ReportRepository to fill data query from DB
 * 
 */

package mum.pmp.mstore.service.report;

public class QuarterlyReportData {
	private String itemId;
	private String itemName;
	private Integer q1Sales;
	private Integer q2Sales;
	private Integer q3Sales;
	private Integer q4Sales;
	private Integer totalSales;
	private Double total;
	
	public QuarterlyReportData(String itemId, String itemName, Integer q1Sales, Integer q2Sales, Integer q3Sales,
			Integer q4Sales, Integer totalSales, Double total) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.q1Sales = q1Sales;
		this.q2Sales = q2Sales;
		this.q3Sales = q3Sales;
		this.q4Sales = q4Sales;
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

	public Integer getQ1Sales() {
		return q1Sales;
	}

	public void setQ1Sales(Integer q1Sales) {
		this.q1Sales = q1Sales;
	}

	public Integer getQ2Sales() {
		return q2Sales;
	}

	public void setQ2Sales(Integer q2Sales) {
		this.q2Sales = q2Sales;
	}

	public Integer getQ3Sales() {
		return q3Sales;
	}

	public void setQ3Sales(Integer q3Sales) {
		this.q3Sales = q3Sales;
	}

	public Integer getQ4Sales() {
		return q4Sales;
	}

	public void setQ4Sales(Integer q4Sales) {
		this.q4Sales = q4Sales;
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
