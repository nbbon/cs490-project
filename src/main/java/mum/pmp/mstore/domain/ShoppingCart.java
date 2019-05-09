/*
 * Author: Yee Mon Zaw
 * Date: 02-May-2019
 * Class Name: ShoppingCart
 * Package: mum.pmp.mstore.domain
 * Description: ShoppingCart Entity
 */

package mum.pmp.mstore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String cartid;
	
    private double totalPrice;
    
    @OneToMany(mappedBy="shoppingCart")
    private List<ShoppingCartLine> cartlineList = new ArrayList<>();
	
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
	public List<ShoppingCartLine> getCartlineList() {
		return cartlineList;
	}
	public void setCartlineList(List<ShoppingCartLine> cartlineList) {
		this.cartlineList = cartlineList;
	}
    
	
}
