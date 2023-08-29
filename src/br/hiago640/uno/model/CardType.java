package br.hiago640.uno.model;

import java.util.Arrays;
import java.util.List;

public enum CardType {

	NUMBER(null), 
	DRAW_TWO_CARD("+2"), 
	REVERSE_CARD("↻"), 
	SKIP_CARD("⨂"), 
	WILD_CARD("⨁"), 
	WILD_DRAW_FOUR_CARD("+4");

	private String value;

	private CardType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static List<CardType> getActionCardsType() {
		return Arrays.asList(DRAW_TWO_CARD, REVERSE_CARD, SKIP_CARD);
	}

	public static List<CardType> getWildCardsType() {
		return Arrays.asList(WILD_CARD, WILD_DRAW_FOUR_CARD);
	}
}
