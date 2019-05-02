package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.CartLineInfo;
import mum.pmp.mstore.domain.Product;

public interface CartInfoService {
	
	 public void addProduct(Product product, int quantity);
	 public int getQuantityTotal();
	 public double getAmountTotal();
	 public void removeProduct(Product product);
	 public List<CartLineInfo> getCartLines();

}
