package mum.pmp.mstore.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class Admin extends Profile{
	
	
	
	
}
