package br.hiago640.uno.model;

import java.util.List;
import java.util.Set;

public class Player {

	private String name;
	private Set<Card> cards;
	private boolean isUno;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public boolean isUno() {
		return isUno;
	}

	public void setUno(boolean isUno) {
		this.isUno = isUno;
	}

}
