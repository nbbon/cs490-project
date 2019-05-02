package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
