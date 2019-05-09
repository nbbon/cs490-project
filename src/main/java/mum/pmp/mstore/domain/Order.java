
package mum.pmp.mstore.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mum.pmp.mstore.model.Address;
import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.model.Customer;

@Entity(name="orders")
@Table(name="orders")
public class Order {
	    @Id
	    private String orderNumber;
	    private LocalDate orderDate;
	    private String status;
	    @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "customer_id")
	    private Customer customer;
	    
	    @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "ADDRESS_ID1")
	    private Address billingAddress;
	    
	    @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "ADDRESS_ID2")
	    private Address shippingAddress;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    private CreditCard creditCard;
	  
	    @OneToMany(cascade = CascadeType.ALL)
	    @JoinColumn(name="order_number")
	    private List<OrderLine> orderlineList = new ArrayList<OrderLine>();
	    
	    public Order() {
	    }

	    public Order(String orderNumber, LocalDate orderDate, String status) {
	        super();
	        this.orderNumber = orderNumber;
	        this.orderDate = orderDate;
	        this.status = status;
	    }


	    public double getTotalPrice() {
	        double totalPrice = 0.0;
	        for (OrderLine oline : orderlineList) {
	            totalPrice = totalPrice + (oline.getProduct().getPrice() * oline.getQuantity());
	        }
	        return totalPrice;
	    }

	    public void addOrderLine(OrderLine oline) {
	        orderlineList.add(oline);
	    }

	    public String getOrderNumber() {
	        return orderNumber;
	    }

	    public void setOrderNumber(String orderNumber) {
	        this.orderNumber = orderNumber;
	    }

	    public LocalDate getOrderDate() {
	        return orderDate;
	    }

	    public void setOrderDate(LocalDate orderDate) {
	        this.orderDate = orderDate;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public List<OrderLine> getOrderlineList() {
	        return orderlineList;
	    }

	    public void setOrderlineList(List<OrderLine> orderlineList) {
	        this.orderlineList = orderlineList;
	    }

	    public void confirm() {
	        status = "confirmed";
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }

		public Address getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(Address billingAddress) {
			this.billingAddress = billingAddress;
		}

		public Address getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(Address shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public CreditCard getCreditCard() {
			return creditCard;
		}

		public void setCreditCard(CreditCard creditCard) {
			this.creditCard = creditCard;
		}
	    
}
