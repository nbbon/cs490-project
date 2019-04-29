package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;

import mum.pmp.mstore.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
