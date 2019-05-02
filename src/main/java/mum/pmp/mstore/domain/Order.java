package mum.pmp.mstore.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Id;
import javax.swing.JList.DropLocation;

import mum.pmp.mstore.model.Address;
import mum.pmp.mstore.model.Customer;

public class Order {
	    @Id
	    private String ordernumber;
	    private LocalDate date;
	    private String status;
	    private Customer customer;
	    
	    private Address billingAddress;
	    private Address shoppingAddress;
	    
	  

	    private ArrayList<OrderLine> orderlineList = new ArrayList<OrderLine>();
	    
	    public Order() {
	    }

	    public Order(String ordernumber, LocalDate date, String status) {
	        super();
	        this.ordernumber = ordernumber;
	        this.date = date;
	        this.status = status;
	    }


	    private double getTotalPrice() {
	        double totalPrice = 0.0;
	        for (OrderLine oline : orderlineList) {
	            totalPrice = totalPrice + (oline.getProduct().getPrice() * oline.getQuantity());
	        }
	        return totalPrice;
	    }

	    public void addOrderLine(OrderLine oline) {
	        orderlineList.add(oline);
	    }

	    public String getOrdernumber() {
	        return ordernumber;
	    }

	    public void setOrdernumber(String ordernumber) {
	        this.ordernumber = ordernumber;
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public ArrayList<OrderLine> getOrderlineList() {
	        return orderlineList;
	    }

	    public void setOrderlineList(ArrayList<OrderLine> orderlineList) {
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

		public Address getShoppingAddress() {
			return shoppingAddress;
		}

		public void setShoppingAddress(Address shoppingAddress) {
			this.shoppingAddress = shoppingAddress;
		}
	    
	    
	    


}
