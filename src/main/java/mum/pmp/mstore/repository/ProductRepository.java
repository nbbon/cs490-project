package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{
	
	Product findByProductNumber(String productNumber);

}
