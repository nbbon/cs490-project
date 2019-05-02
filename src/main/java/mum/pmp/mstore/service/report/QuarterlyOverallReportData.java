package mum.pmp.mstore.service.report;

public class QuarterlyOverallReportData {
	private Integer q1Sales;
	private Integer q2Sales;
	private Integer q3Sales;
	private Integer q4Sales;
	private Integer totalSales;
	private Double total;
	
	public QuarterlyOverallReportData(Integer q1Sales, Integer q2Sales, Integer q3Sales, Integer q4Sales,
			Integer totalSales, Double total) {
		super();
		this.q1Sales = q1Sales;
		this.q2Sales = q2Sales;
		this.q3Sales = q3Sales;
		this.q4Sales = q4Sales;
		this.totalSales = totalSales;
		this.total = total;
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
