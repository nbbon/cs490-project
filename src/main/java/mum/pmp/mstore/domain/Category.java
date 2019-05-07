package mum.pmp.mstore.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import mum.pmp.mstore.model.Vendor;


@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryID;
	@NotEmpty(message = "{categoryName.empty}")
	private String categoryName;
	private String description;
	
	  @ManyToOne(fetch = FetchType.EAGER)
	  
	  @JoinColumn(name = "VENDOR_NUMBER") private Vendor vendor;
	  
	  @OneToMany private List<Product> products;
	 

	
	  public Vendor getVendor() { return vendor; }
	  
	  public void setVendor(Vendor vendor) { this.vendor = vendor; }
	  
	  public List<Product> getProducts() { return products; }
	  
	  public void setProducts(List<Product> products) { this.products = products; }
	 

	public Category() {	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public List<Product> getProducts() { return products; }
	 * 
	 * 
	 * 
	 * public void setProducts(List<Product> products) { this.products = products; }
	 * 
	 * 
	 * 
	 * public boolean addProduct(Product product){
	 * 
	 * boolean success=false; if( products.add(product)){ product.setCategory(this);
	 * success=true; } return success; }
	 * 
	 * public boolean removeProduct(Product product){ boolean success=false;
	 * 
	 * if(products.remove(product)){ product.setCategory(null); success=true; }
	 * 
	 * return success; }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryID;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Category other = (Category) obj;
		if (categoryID != other.categoryID)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
