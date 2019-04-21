package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.Stock;

public interface ProductService {
	void addProduct(String productNumber, String productName, int price, String description, int categoryID);
	Product getProduct(String productNumber);
	void deleteProduct(String productNumber);
	List<Product> getProducts();
	void setStock(String productNumber, int quantity, String locationCode);
    Stock getStock(String productNumber);
    
   

}
