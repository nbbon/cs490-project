package mum.pmp.mstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	//@Query("from Category c where c.vendor.vendorNumber=:vendornum")
	//@Query("select c.categoryName from Category c where  c.id=:vendornum")
	//public List<Category> getVendorCategories(@Param(value = "vendornum") String vendornum);
	
	
//	public List<Category> findCategoriesByVendor()
	
	/*
	 * @Query("from Category c where c.categoryName=:categoryName") public
	 * List<Product> getAllCategoryProduct(@Param(value = "categoryName") String
	 * categoryName);
	 */
}
