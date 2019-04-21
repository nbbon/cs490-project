package mum.pmp.mstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.Stock;
import mum.pmp.mstore.repository.ProductRepository;
import mum.pmp.mstore.service.CategoryService;
import mum.pmp.mstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryService categoryService;

	@Override
	public Product getProduct(String productNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(String productNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProduct(String productNumber, String productName, int price, String description, int categoryID) {
      Category category = categoryService.getCategory(categoryID);
		
       if (category!=null) {
    	   Product product = new Product(productNumber, productName, price, description);
   		   product.setCategory(category);
    	   productRepository.save(product);	
       }		
		
				
	}

	@Override
	public void setStock(String productNumber, int quantity, String locationCode) {
	
		Optional<Product> optional= productRepository.findById(productNumber);
        if (optional.isPresent()) {
        	Product product = optional.get();
        	Stock stock = new Stock(quantity, locationCode);
        	product.setStock(stock);
        	productRepository.save(product);
			
		}		
		
		
		
	}

	@Override
	public Stock getStock(String productNumber) {
		
		Optional<Product> optional = productRepository.findById(productNumber);
		if (optional.isPresent()) {
			Product product = optional.get();
			Stock stock = product.getStock();
			return stock;
			
		}
		
		return null;
	}

	

}
