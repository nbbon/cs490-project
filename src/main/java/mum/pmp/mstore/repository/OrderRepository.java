
package mum.pmp.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

}

