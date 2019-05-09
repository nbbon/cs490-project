/*
 * Author: Niveen Abdelaatty
 * Date: 1-May-2019
 * Class Name: SearchRepository
 * Package: repository
 * Description: describe how we create database queries from the method names of query methods
 * 
 */
package mum.pmp.mstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Product;

@Repository
public interface SearchRepository extends JpaRepository<Product, Double> {

	@Query("from Product where productName=:productName")
	List<Product> findProductByName(@Param(value = "productName") String productName);

	@Query("from Product where productNumber=:productNumber")
	List<Product> findProductByNum(@Param(value = "productNumber") String productNumber);

	@Query("from Product where price=:price")
	List<Product> findProductByPrice(@Param(value = "price") double price);

	@Query("from Product where productNumber=:productNumber or productName=:productName")
	List<Product> findProductByNaNu(@Param(value = "productNumber") String productNumber,
			@Param(value = "productName") String productName);

	@Query("from Product where productName=:productName or price=:price")
	List<Product> findProductByNaP(@Param(value = "productName") String productName,
			@Param(value = "price") double price);

	@Query("from Product where productNumber=:productNumber or price=:price")
	List<Product> findProductByNuP(@Param(value = "productNumber") String productNumber,
			@Param(value = "price") double price);

	@Query("from Product where productNumber=:productNumber and productName=:productName and price=:price")
	List<Product> findProductBy(@Param(value = "productNumber") String productNumber,
			@Param(value = "productName") String productName, @Param(value = "price") double price);

}
