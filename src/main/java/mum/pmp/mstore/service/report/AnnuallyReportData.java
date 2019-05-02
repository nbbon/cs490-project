package mum.pmp.mstore.service.report;

public class AnnuallyReportData {
	private String itemId;
	private String itemName;
	private Integer yearSales;
	private Integer totalSales;
	private Double total;
	public AnnuallyReportData(String itemId, String itemName, Integer yearSales, Integer totalSales, Double total) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.yearSales = yearSales;
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
