package mum.pmp.mstore.service.report;

import java.time.LocalDate;

public class AdhocReportData {
	private String itemId;
	private String itemName;
	private LocalDate rangeSales;
	private Integer totalSales;
	private Double total;
	public AdhocReportData(String itemId, String itemName, LocalDate rangeSales, Integer totalSales, Double total) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.rangeSales = rangeSales;
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
