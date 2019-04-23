package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.Stock;

public interface ProductService {
	void addProduct(Product product);
	Product getProduct(Integer id);
	void deleteProduct(Integer id);
	List<Product> getProducts();
	void setStock(String productNumber, int quantity, String locationCode);
    Stock getStock(String productNumber);
    
   

}
