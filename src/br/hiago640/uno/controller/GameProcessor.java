package br.hiago640.uno.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.Player;

public class GameProcessor {

	private List<Card> cards;
	private List<Card> discardedCards = new ArrayList<>();
	private Set<Player> players = new LinkedHashSet<>();

	public GameProcessor() {

		createDeckCards();
		createPlayers();

	}

	private void createDeckCards() {
		cards = CardProcessor.createDeck();
		Collections.shuffle(cards);
	}

	private void createPlayers() {
		
	}

}
