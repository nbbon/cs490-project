package mum.pmp.mstore.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stock implements Serializable{

	private static final long serialVersionUID = -4210144688196855737L;


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "STOCK_ID")
	private int id;
	private int quantity;
	private String locationCode;
	
	 @OneToOne(mappedBy = "stock", cascade = CascadeType.ALL)
	 private Product product;;
	
	public Stock() {
		
	}
	
	

	public Stock(int quantity, String locationCode) {
		super();
		this.quantity = quantity;
		this.locationCode = locationCode;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationCode == null) ? 0 : locationCode.hashCode());
		result = prime * result + quantity;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (locationCode == null) {
			if (other.locationCode != null)
				return false;
		} else if (!locationCode.equals(other.locationCode))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	
	
	
	

}
