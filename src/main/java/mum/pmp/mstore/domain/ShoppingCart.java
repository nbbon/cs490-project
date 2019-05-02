package mum.pmp.mstore.domain;

import java.util.ArrayList;

public class ShoppingCart {
	private String cartid;
    private double totalPrice;
    private ArrayList<ShoppingCartLine> cartlineList = new ArrayList<>();
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public ArrayList<ShoppingCartLine> getCartlineList() {
		return cartlineList;
	}
	public void setCartlineList(ArrayList<ShoppingCartLine> cartlineList) {
		this.cartlineList = cartlineList;
	}
    
    
    
    


}
