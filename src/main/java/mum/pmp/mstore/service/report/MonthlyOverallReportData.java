/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: MonthlyOverallReportData
 * Module: Report
 * Description: The report data holder class, store data for monthly overall report 
 * Used by ReportRepository to fill data query from DB
 * 
 */

package mum.pmp.mstore.service.report;

public class MonthlyOverallReportData {
	private Integer m1Sales;
	private Integer m2Sales;
	private Integer m3Sales;
	private Integer m4Sales;
	private Integer m5Sales;
	private Integer m6Sales;
	private Integer m7Sales;
	private Integer m8Sales;
	private Integer m9Sales;
	private Integer m10Sales;
	private Integer m11Sales;
	private Integer m12Sales;
	private Integer totalSales;
	private Double total;
	
	public MonthlyOverallReportData(Integer m1Sales, Integer m2Sales, Integer m3Sales, Integer m4Sales, Integer m5Sales,
			Integer m6Sales, Integer m7Sales, Integer m8Sales, Integer m9Sales, Integer m10Sales, Integer m11Sales,
			Integer m12Sales, Integer totalSales, Double total) {
		this.m1Sales = m1Sales;
		this.m2Sales = m2Sales;
		this.m3Sales = m3Sales;
		this.m4Sales = m4Sales;
		this.m5Sales = m5Sales;
		this.m6Sales = m6Sales;
		this.m7Sales = m7Sales;
		this.m8Sales = m8Sales;
		this.m9Sales = m9Sales;
		this.m10Sales = m10Sales;
		this.m11Sales = m11Sales;
		this.m12Sales = m12Sales;
		this.totalSales = totalSales;
		this.total = total;
	}

	public Integer getM1Sales() {
		return m1Sales;
	}

	public void setM1Sales(Integer m1Sales) {
		this.m1Sales = m1Sales;
	}

	public Integer getM2Sales() {
		return m2Sales;
	}

	public void setM2Sales(Integer m2Sales) {
		this.m2Sales = m2Sales;
	}

	public Integer getM3Sales() {
		return m3Sales;
	}

	public void setM3Sales(Integer m3Sales) {
		this.m3Sales = m3Sales;
	}

	public Integer getM4Sales() {
		return m4Sales;
	}

	public void setM4Sales(Integer m4Sales) {
		this.m4Sales = m4Sales;
	}

	public Integer getM5Sales() {
		return m5Sales;
	}

	public void setM5Sales(Integer m5Sales) {
		this.m5Sales = m5Sales;
	}

	public Integer getM6Sales() {
		return m6Sales;
	}

	public void setM6Sales(Integer m6Sales) {
		this.m6Sales = m6Sales;
	}

	public Integer getM7Sales() {
		return m7Sales;
	}

	public void setM7Sales(Integer m7Sales) {
		this.m7Sales = m7Sales;
	}

	public Integer getM8Sales() {
		return m8Sales;
	}

	public void setM8Sales(Integer m8Sales) {
		this.m8Sales = m8Sales;
	}

	public Integer getM9Sales() {
		return m9Sales;
	}

	public void setM9Sales(Integer m9Sales) {
		this.m9Sales = m9Sales;
	}

	public Integer getM10Sales() {
		return m10Sales;
	}

	public void setM10Sales(Integer m10Sales) {
		this.m10Sales = m10Sales;
	}

	public Integer getM11Sales() {
		return m11Sales;
	}

	public void setM11Sales(Integer m11Sales) {
		this.m11Sales = m11Sales;
	}

	public Integer getM12Sales() {
		return m12Sales;
	}

	public void setM12Sales(Integer m12Sales) {
		this.m12Sales = m12Sales;
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
