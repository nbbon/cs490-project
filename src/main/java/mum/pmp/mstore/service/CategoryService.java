package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;

public interface CategoryService {
	void addCategory(Category category);
	Category getCategory(Integer id);
	List<Category> getCategories();
	List<Category> getCategoriesByVendorId(String vendornum);
	List<Product> getAllCategoryProduct(String categoryName);
	void deleteCategory(Integer id);

}
