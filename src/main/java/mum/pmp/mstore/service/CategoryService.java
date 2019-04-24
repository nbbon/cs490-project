package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Category;

public interface CategoryService {
	void addCategory(Category category);
	Category getCategory(Integer id);
	List<Category> getCategories();
	void deleteCategory(Integer id);

}
