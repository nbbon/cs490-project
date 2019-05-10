/*
 * Author: Jean Chrisner Jean Charles
 * Date: 24-Apr-2019
 * Class Name: StockRepository
 * Package: repository
 * Description: provides the mechanism for storage, retrieval, search, update and delete operation on objects
 * 
 */

package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer>{

}
