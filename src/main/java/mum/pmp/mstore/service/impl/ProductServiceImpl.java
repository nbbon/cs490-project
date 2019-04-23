package mum.pmp.mstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.Stock;
import mum.pmp.mstore.repository.CategoryRepository;
import mum.pmp.mstore.repository.ProductRepository;
import mum.pmp.mstore.repository.StockRepository;
import mum.pmp.mstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryService;
	
	@Autowired
	StockRepository stockRepository;

	@Override
	public Product getProduct(Integer id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			
			return optional.get();
			
		}
		return null;
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<Product> getProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void addProduct(Product product) {
		Optional<Category> optional = categoryService.findById(product.getCategory().getCategoryID());
		
       if (optional.isPresent()) {
   		   product.setCategory(optional.get());
   		   Stock stock = new Stock();
   		   stock.setQuantity(product.getStock().getQuantity());
   		   stockRepository.save(stock);
   		   product.setStock(stock);
   		   
    	   productRepository.save(product);	
       }		
					
	}
	
	@Override
	
	  public void setStock(String productnumber, int quantity, String locationcode) {
	        Product product = productRepository.findByProductNumber(productnumber);
	        if (product!=null) {
	            Stock stock = new Stock(quantity, locationcode);
	            stockRepository.save(stock);
	            product.setStock(stock);
	            productRepository.save(product);
	        }
	    }
	
	@Override

	    public Stock getStock(String productnumber) {
        		Product product = productRepository.findByProductNumber(productnumber);
	        if (product!=null) {
	            Stock stock = product.getStock();
	            return stock;
	        }
	        return null;
	    }
	
	
	
}
