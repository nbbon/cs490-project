package mum.pmp.mstore.service.report;

public class HalfYearlyOverallReportData {
	private Integer fhSales;
	private Integer shSales;
	private Integer totalSales;
	private Double total;
	
	public HalfYearlyOverallReportData(Integer fhSales, Integer shSales, Integer totalSales, Double total) {
		super();
		this.fhSales = fhSales;
		this.shSales = shSales;
		this.totalSales = totalSales;
		this.total = total;
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
