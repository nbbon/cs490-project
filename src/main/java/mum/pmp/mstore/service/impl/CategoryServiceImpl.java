package mum.pmp.mstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.repository.CategoryRepository;
import mum.pmp.mstore.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void addCategory(int categoryID, String categoryName, String description) {

		Category category = new Category(categoryID, categoryName, description);
		categoryRepository.save(category);
	}

	@Override
	public Category getCategory(int id) {
	     Optional<Category> optional = categoryRepository.findById(id);
	     if (optional.isPresent()) {
	    	 return optional.get();
			
		}
	

		return null;
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public void deleteCategory(int id) {
		 Optional<Category> optional = categoryRepository.findById(id);
	     if (optional.isPresent()) {
	    	 categoryRepository.deleteById(id);	
		}		
	}

}
