/*
 * Author: Niveen Abdelaatty
 * Date: 24-Apr-2019
 * Class Name: CategoryService
 * Package: service
 * Description: contains the business logic of category module to operate on the data sent to and from the DAO and the client.
 * 
 */
package mum.pmp.mstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.repository.CategoryRepository;
import mum.pmp.mstore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category getCategory(Integer id) {
		Optional<Category> optional = categoryRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Category> getCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);

	}
	/*
	 * @Override public List<Category> getCategoriesByVendorId(String vendornum) {
	 * System.out.println("getCategoriesByVendorId: "+vendornum); return
	 * categoryRepository.getVendorCategories(vendornum); }
	 * 
	 * @Override public List<Product> getAllCategoryProduct(String categoryName) {
	 * return categoryRepository.getAllCategoryProduct(categoryName); }
	 */

}
