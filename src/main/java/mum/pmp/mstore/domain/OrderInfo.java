package mum.pmp.mstore.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mum.pmp.mstore.model.Customer;


/*@Entity
@Table(name = "Orders", //
       uniqueConstraints = { @UniqueConstraint(columnNames = "Order_Num") })*/
public class OrderInfo  {
	    
	@Id
	private int orderNum;
	private Date orderDate;
	private double amount;
	private Customer customer;
	private List<OrderDetailInfo> details;
	
	
	
	public OrderInfo() {
		super();
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderDetailInfo> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetailInfo> details) {
		this.details = details;
	}	
	
	
	
	


}
