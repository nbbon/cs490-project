package mum.pmp.mstore.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "VENDOR")
public class Vendor extends Person{
	
	@Column(name="VENDOR_NUMBER")
	private String vendorNumber;
	
	@Column(name="VENDOR_NAME")
	private String vendorName;
	
	@Column(name="REG_ID")
	private String regId;
	
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

	@Override
	public String toString() {
		return "Vendor [vendorNumber=" + vendorNumber + ", vendorName=" + vendorName + ", regId=" + regId
				+ ", contactPerson=" + contactPerson + "]";
	}

	
}
