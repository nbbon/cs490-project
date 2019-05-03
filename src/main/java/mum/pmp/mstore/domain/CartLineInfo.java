package mum.pmp.mstore.domain;

import org.springframework.stereotype.Component;

@Component
public class CartLineInfo {
	private int quantity;
	private Product product;
	public CartLineInfo() {
    quantity=0;
   }
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public double getAmount() {
        return    this.product.getPrice()* this.quantity;
    }
	
	
	

}
