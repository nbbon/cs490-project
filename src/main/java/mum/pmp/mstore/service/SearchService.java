package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;


public interface SearchService {

	public List<Category> getAllCategories(String searchTerm);
	
	public List<Product> getAllProducts(String searchTerm);

}