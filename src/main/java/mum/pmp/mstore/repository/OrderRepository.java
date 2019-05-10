/*
 * Author: Jean Chrisner Jean Charles
 * Date: 29-Apr-2019
 * Class Name: OrderRepository
 * Package: repository
 * Description: provides the mechanism for storage, retrieval, search, update and delete operation on objects
 * 
 */
package mum.pmp.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

}

