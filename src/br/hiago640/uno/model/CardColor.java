package br.hiago640.uno.model;

public enum CardColor {

	RED("1 - Red"), GREEN("2 - Green"), BLUE("3 - Blue"), YELLOW("4 - Yellow");
	
	private String color;
	
	private CardColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
}
