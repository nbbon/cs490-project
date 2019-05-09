package mum.pmp.mstore.model;

import javax.persistence.*;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: Customer
 * Module: Customer Registration
 * Description: Customer entity that will persist in the database. 
 * 
 */

@Entity
@DiscriminatorValue(value="CUSTOMER")
public class Customer extends Profile{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
}
