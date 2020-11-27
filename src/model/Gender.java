package model;

public enum Gender {
	MALE(0, "Masculino"),
	FEMALE(1, "Feminino");
	
	Gender(int value, String label) {
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
	
	public static Gender valueOf(int value) {
		for (Gender gender : Gender.values()) {
			if (gender.getValue() == value) {
				return gender;
			}
		}
		return null;
	}
	
}
