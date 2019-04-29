package mum.pmp.mstore.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	@Query(value = 
			"SELECT o.orderDate, o.orderNumber FROM ORDERS o "
			+ "WHERE o.orderDate >= :fromDate AND o.orderDate <= :toDate "
			+ "GROUP BY o.orderDate, o.orderNumber "
			+ "ORDER BY o.orderDate, o.orderNumber")
	List<Order> findBetweenDates(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

}
