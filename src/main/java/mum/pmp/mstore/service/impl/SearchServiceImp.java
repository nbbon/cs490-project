
package mum.pmp.mstore.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.repository.CategoryRepository;
import mum.pmp.mstore.repository.ProductRepository;
import mum.pmp.mstore.repository.SearchRepository;
import mum.pmp.mstore.service.SearchService;

@Service
public class SearchServiceImp implements SearchService {

	@Resource
	private ProductRepository productRepository;
	@Resource
	private CategoryRepository categoryRepository;
	@Resource
	private SearchRepository searchRepository;

	@Override
	public List<Category> getAllCategories() {
		return (List<Category>) searchRepository.getAllCategories();
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) searchRepository.getAllProducts();
	}
}

/*
 * @Autowired private ProductRepository productRepository;
 * 
 * @Override public List<Product> findProductByProductNumber(String
 * productNumber) { List<Product> product =
 * productRepository.findProductByProductNumber(productNumber); return product;
 * }
 * 
 * @Override public List<Product> findByProductName(String productName) {
 * List<Product> productList = productRepository.findByProductName(productName);
 * return productList;
 */
