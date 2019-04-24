package mum.pmp.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.pmp.mstore.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
