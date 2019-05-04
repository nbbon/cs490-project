package mum.pmp.mstore.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="SUPER_ADMIN")
public class SuperAdmin extends Profile{

}
