package mum.pmp.mstore.domain;

import java.util.ArrayList;
import java.util.List;

import mum.pmp.mstore.model.Customer;

public class CartInfo {

	private int orderNum;
	 
    private Customer customer;
 
    private  List<CartLineInfo> cartLines;

	public CartInfo() {
		super();
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartLineInfo> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLineInfo> cartLines) {
		this.cartLines = cartLines;
	}
    
    
    
    
    
    
    
    
	
}
