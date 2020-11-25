package model;

import javax.persistence.Entity;

@Entity
public class Profile extends DefaultEntity<Profile> {

	private static final long serialVersionUID = -8796475753625016536L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
