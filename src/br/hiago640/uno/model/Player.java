package br.hiago640.uno.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.hiago640.uno.controller.PlayerProcessor;

public class Player {

	private String name;
	private List<Card> cards = new ArrayList<>();
	private boolean isUno;
	private boolean isSkipped;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public boolean isUno() {
		return isUno;
	}

	public void setUno(boolean isUno) {
		this.isUno = isUno;
	}

	public boolean isSkipped() {
		return isSkipped;
	}

	public void setSkipped(boolean isSkipped) {
		this.isSkipped = isSkipped;
	}

	@Override
	public String toString() {
		return String.format("Player [name=%s]", name);
	}

	public void showPlayerHand() {
		System.out.println("==========================================================");
		cards.stream().forEach(e -> {
			String msg = String.format("%s - %s", cards.indexOf(e) + 1, e);
			System.out.println(msg);
		});
		System.out.println("==========================================================");
	}

}
