package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer>{

}
