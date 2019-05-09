package mum.pmp.mstore.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{
	
	Optional<Product> findByProductNumber(String productNumber);
	
	List<Product> findProductsByProductName(String name);
	
}
