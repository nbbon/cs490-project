package mum.pmp.mstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	
	@Column(name="USERID", nullable= false, length= 30, unique = true)
	private String userId;
	
	@Column(name="PASSWORD", length= 60)
	private String password;
	
	@Column(name="ENABLED")
	private boolean enabled;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String email) {
		this.userId = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void setRole(Role role) {
		roles.add(role);
	}
	
	
	public void removeRole(Role role) {
        this.roles.remove(role);
	}
	
	public void clearRoles() {
        roles.clear();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + userId + ", password=" + password + ", enabled=" + enabled + ", roles="
				+ roles + "]";
	}
	
	

}