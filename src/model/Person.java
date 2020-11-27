package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Person extends DefaultEntity<Person>{

	private static final long serialVersionUID = 3640341373230144144L;
	
	@Column(nullable = false)
	@NotBlank(message = "Campo nome é obrigatório.")
	private String name;
	
	@Column(nullable = false)
	@NotBlank(message = "Campo cpf é obrigatório.")
	private String cpf;
	
	@Column
	private Date birthDate;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
