package mum.pmp.mstore.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	Optional<Product> findByProductNumber(String productNumber);
	
	List<Product> findProductsByProductName(String name);
	
	@Query("from Product where vendor_number=:vendorNumber")
	List<Product> findByVendorNumber(@Param(value="vendorNumber") Integer vendorNumber);
	
	@Query("delete from Product where id=:productId")
	void deleteProduct(@Param(value="productId") Integer productId);
	
}
