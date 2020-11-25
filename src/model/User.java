package model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class User extends Person{
	
	private static final long serialVersionUID = -7867179292217897095L;
	
	private Role role;
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
