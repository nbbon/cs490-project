
package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String>{

}

