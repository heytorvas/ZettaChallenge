package model;

public enum Gender {
	MALE(0, "Masculino"),
	FEMALE(1, "Feminino");
	
	private Gender(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	private int value;
	private String label;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
