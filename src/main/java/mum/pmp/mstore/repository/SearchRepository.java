package mum.pmp.mstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;

@Repository
public interface SearchRepository extends JpaRepository<Product, Integer> {


	@Query("from Product where productNumber=:productNumber or productName=:productName or price=:price or category=:category")
	List<Product> findProductBy(@Param(value = "productNumber") String productNumber,
			@Param(value = "productName") String productName, @Param(value = "price") int price,
			@Param(value = "category") Category category);


	
	@Query("select * from category")
	List<Category> getAllCategories();
	 
	@Query("select * from product")
	List<Product> getAllProducts();
	
}
