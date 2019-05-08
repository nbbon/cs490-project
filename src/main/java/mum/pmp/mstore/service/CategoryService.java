/*
 * Author: Niveen Abdelaatty
 * Date: 24-Apr-2019
 * Class Name: CategoryService
 * Package: service
 * Description: contains the business logic of category module to operate on the data sent to and from the DAO and the client.
 * 
 */
package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Category;

public interface CategoryService {

	void addCategory(Category category);

	Category getCategory(Integer id);

	List<Category> getCategories();

	void deleteCategory(Integer id);

}
