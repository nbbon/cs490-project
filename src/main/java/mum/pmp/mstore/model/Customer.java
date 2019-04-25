package mum.pmp.mstore.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="CUSTOMER")
public class Customer extends Person{
	
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
