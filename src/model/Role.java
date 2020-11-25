package model;

import javax.persistence.Entity;

@Entity
public class Role extends DefaultEntity<Role>{
	
	private static final long serialVersionUID = -340356079320151595L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
