package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Profile extends DefaultEntity<Profile> {
	
	private static final long serialVersionUID = -340356079320151595L;
	
	@Column(nullable = false)
	@NotBlank(message = "Campo nome é obrigatório.")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}