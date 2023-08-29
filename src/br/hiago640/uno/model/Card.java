package br.hiago640.uno.model;

public class Card {

	private String value;
	private CardColor color;
	private CardType type;

	public Card(String value, CardColor color, CardType type) {
		this.value = value;
		this.color = color;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CardColor getColor() {
		return color;
	}

	public void setColor(CardColor color) {
		this.color = color;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("Card [value=%s, color=%s, type=%s]", value, color, type);
	}

}
