//package mum.pmp.mstore.domain;
//
//
//import java.time.LocalDate;
//import java.util.List;
//
//import javax.persistence.Entity;
//
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//
//import mum.pmp.mstore.model.Customer;
//
//@Entity
//public class Order {
//
//	@Id
//	private Long orderNumber;
//	private LocalDate date;
//	private String status;
//	@OneToMany
//	@JoinColumn(name = "orderNumber")
//	private List<OrderLine> orderLine;
//	private Customer customer;
//	private Address billingAddress;
//	private Address shippingAddress;
//
//	
//
//	public Order() { }
//	
//	
//	
//    public void addOrderLine(OrderLine oline) {
//    	orderLine.add(oline);
//    }
//
//	
//	public Order( Long orderNumber, LocalDate date, String status) {
//		this.orderNumber=orderNumber;
//		this.date=date;
//		this.status=status;
//		
//	}
//
//	public Long getOrderNumber() {
//		return orderNumber;
//	}
//
//	public void setOrderNumber(Long orderNumber) {
//		this.orderNumber = orderNumber;
//	}
//
//	public LocalDate getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public List<OrderLine> getOrderLine() {
//		return orderLine;
//	}
//
//	public void setOrderLine(List<OrderLine> orderLine) {
//		this.orderLine = orderLine;
//	}
//
//	public Address getBillingAddress() {
//		return billingAddress;
//	}
//
//	public void setBillingAddress(Address billingAddress) {
//		this.billingAddress = billingAddress;
//	}
//
//	public Address getShippingAddress() {
//		return shippingAddress;
//	}
//
//	public void setShippingAddress(Address shippingAddress) {
//		this.shippingAddress = shippingAddress;
//	}
//
//
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//	
//	
//	
//
//}
