package br.hiago640.uno.controller;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.CardColor;
import br.hiago640.uno.model.CardType;

public class CardProcessor {

	public static List<Card> createDeck() {
		// A UNO deck consists of 108 cards, of which there are 76 Number cards, 24
		// Action cards and 8 Wild cards.

		List<Card> cardList = new ArrayList<>();

		// Each color contains 19 cards, one number 0 card and two sets of cards
		// numbered 1-9.
		for (CardColor color : CardColor.values()) {

			// Number cards
			int value = 0;
			for (int i = 0; i < 19; i++) {
//				value = ((i % 2 == 0) ? i : (1 + i)) / 2;
				
				value = ((i % 2) + i) / 2;

				cardList.add(new Card(String.valueOf(value), color, CardType.NUMBER));
			}

			// Action Cards
			for (CardType type : CardType.getActionCardsType())
				for (int i = 0; i < 2; i++)
					cardList.add(new Card(type.getValue(), color, type));

			// Wild Cards
			for (CardType type : CardType.getWildCardsType())
				cardList.add(new Card(type.getValue(), null, type));

		}

		return cardList;
	}

	public static Card getLastCardDeck(Deque<Card> discardedDeck) {
		return discardedDeck.peek();
	}

	
	public static void discardedCards(Card card, Deque<Card> discardedCard) {
		discardedCard.push(card);
	}
}
