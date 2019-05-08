package mum.pmp.mstore.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.repository.CategoryRepository;
import mum.pmp.mstore.repository.ProductRepository;
import mum.pmp.mstore.service.SearchService;

@Service
public class SearchServiceImp implements SearchService {

	@Resource
	private ProductRepository productRepository;
	@Resource
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories(String searchTerm) {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public List<Product> getAllProducts(String searchTerm) {
		return (List<Product>) productRepository.findAll();
	}
}