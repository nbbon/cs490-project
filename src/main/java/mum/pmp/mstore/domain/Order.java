package mum.pmp.mstore.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity(name = "ORDERS")
@Table(name = "ORDERS")
public class Order {
	@Id
	private Long orderNumber;
	
	private LocalDate orderDate;
	
	private String status;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JoinColumn(name = "orderNumber")
	private List<OrderLine> orderLines;

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getDate() {
		return orderDate;
	}

	public void setDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

}
