/*
 * Author: Jean Chrisner Jean Charles
 * Date: 24-Apr-2019
 * Class Name: ProductServiceImpl
 * Package: service
 * Description: contains the business logic of product module to operate on the data sent to and from the DAO and the client.
 * 
 */

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
	public Product getProductByProductNumber(String productNumber) {
		Optional<Product> optional = productRepository.findByProductNumber(productNumber);
		return optional.orElse(null);
	}

	@Override
	public void deleteProduct(Integer id) {
		System.out.println("delete this product >> " + id);
		//productRepository.deleteById(id);
		productRepository.deleteProduct(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void addProduct(Product product) {
		Optional<Category> optional = categoryService.findById(product.getCategory().getCategoryID());
		
		System.out.print(optional.get()+ " tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
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
	        Optional<Product> optional = productRepository.findByProductNumber(productnumber);
	        Product product = optional.orElse(null);
	        if (product!=null) {
	            Stock stock = new Stock(quantity, locationcode);
	            stockRepository.save(stock);
	            product.setStock(stock);
	            productRepository.save(product);
	        }
	    }
	
	@Override

	    public Stock getStock(String productnumber) {
		Optional<Product> optional = productRepository.findByProductNumber(productnumber);
        Product product = optional.orElse(null);
	        if (product!=null) {
	            Stock stock = product.getStock();
	            return stock;
	        }
	        return null;
	    }

	@Override
	public List<Product> findProductsByProductName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findProductsByProductName(name);
	}

	@Override
	public Optional<Product> getProduct(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}
	
	
	@Override
	public List<Product> findProductsByVendor(int vendorNumber) {
		return productRepository.findByVendorNumber(vendorNumber);
	}
	
	
	
}
