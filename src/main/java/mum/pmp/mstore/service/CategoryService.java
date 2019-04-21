package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Category;

public interface CategoryService {
	void addCategory(int categoryID, String categoryName, String description);
	Category getCategory(int id);
	List<Category> getCategories();
	void deleteCategory(int id);

}
