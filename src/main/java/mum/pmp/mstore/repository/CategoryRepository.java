package mum.pmp.mstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	public List<Category> vendorCategories(Integer vendorId);

}
