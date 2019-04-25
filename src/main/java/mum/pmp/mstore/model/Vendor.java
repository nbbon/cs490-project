package mum.pmp.mstore.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "VENDOR")
public class Vendor extends Person{
	
	@NotNull
	@Size(min=2,max=20)
	@Column(name="VENDOR_NUMBER")
	private String vendorNumber;
	
	@NotNull
	@Size(min=2, max=15)
	@Column(name="VENDOR_NAME")
	private String vendorName;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(name="REG_ID")
	private String regId;
	
	@NotNull
	@Size(min=2, max=20)
	@Column(name="CONTACT_PERSON")
	private String contactPerson;

	
	public String getVendorNumber() {
		return vendorNumber;
	}

	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	
}
