//package mum.pmp.mstore.domain;
//
//import java.util.ArrayList;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//
//@Entity
//public class ShoppingCart {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long cartId;
//	@OneToMany
//	@JoinColumn(name = "cartId")
//	private ArrayList<ShoppingCartLine>  shoppingCartLines;
//	
//
//	ShoppingCart() {
//	}
//
//	public Long getCartId() {
//		return cartId;
//	}
//
//	public void setCartId(Long cartId) {
//		this.cartId = cartId;
//	}
//
//	public ArrayList<ShoppingCartLine> getShoppingCartLines() {
//		return shoppingCartLines;
//	}
//
//	public void setShoppingCartLines(ArrayList<ShoppingCartLine> shoppingCartLines) {
//		this.shoppingCartLines = shoppingCartLines;
//	}
//
//	
//
//}
