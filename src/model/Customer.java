package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Customer extends Person {

	private static final long serialVersionUID = 8364239647574512618L;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_profile", 
			   joinColumns = { @JoinColumn(name = "customer_id") }, 
			   				   inverseJoinColumns = {@JoinColumn(name = "profile_id") }
	)
	private List<Profile> profileList;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}

}
