package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
