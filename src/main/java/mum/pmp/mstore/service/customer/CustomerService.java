package mum.pmp.mstore.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerInterface{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

}
