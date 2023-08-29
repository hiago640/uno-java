package br.hiago640.uno.controller;

import java.util.Deque;
import java.util.List;

import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.Player;

public class PlayerProcessor {

	public static void drawCards(Player player, List<Card> deck, int amount) {
		for(int i = 0; i < amount; i++)
			drawCard(player, deck);
	}	
	
	public static void drawCard(Player player, List<Card> deck) {
		player.getCards().add(deck.remove(0));
	}

	public static void discardedCards(Card card, Deque<Card> discardedCard) {
		CardProcessor.discardedCards(card, discardedCard);
	}

	public static void showPlayerHand(Player player) {
		player.getCards().stream().
			forEach(e -> {
				String msg = String.format("%s - %s", player.getCards().indexOf(e) + 1, e);
				System.out.println(msg);
			});
	}
}
