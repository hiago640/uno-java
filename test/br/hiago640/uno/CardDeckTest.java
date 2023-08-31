package br.hiago640.uno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.hiago640.uno.controller.CardProcessor;
import br.hiago640.uno.controller.PlayerProcessor;
import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.CardColor;
import br.hiago640.uno.model.CardType;
import br.hiago640.uno.model.Player;

class CardDeckTest {

	private List<Card> deck;
	private Player p1 = new Player("Player1");
	private Player p2 = new Player("Player2");
	private Player p3 = new Player("Player3");
	private Card skipCard = new Card(CardType.SKIP_CARD.getValue(), CardColor.RED, CardType.SKIP_CARD);
	private List<Player> players = new LinkedList<>();
	
	@BeforeEach
	void initializeDeck() {
		deck = CardProcessor.createDeck();
	}
	
	@Test
	@Order(1)
	void generateDeckTest() {
		assertEquals(108, deck.size(), "Amount of Cards");
		assertEquals(76, deck.stream().filter(c -> CardType.NUMBER.equals(c.getType())).count(), "Amount of Number Cards ");
		assertEquals(24, deck.stream().filter(c -> CardType.getActionCardsType().contains(c.getType())).count(), "Amount of Action Cards ");
		assertEquals(8, deck.stream().filter(c -> CardType.getWildCardsType().contains(c.getType())).count(), "Amount of Wild Cards");
	}
	
	@Test
	@Order(2)
	void shuffleTest() {
		Card firstCard = deck.get(0);
		Collections.shuffle(deck);
		
		assertFalse(deck.get(0).equals(firstCard));
	}

	@Test
	@Order(3)
	void drawCardsTest() {
		PlayerProcessor.drawCards(p1, deck, 3);
		PlayerProcessor.drawCards(p2, deck, 3);
		PlayerProcessor.drawCards(p3, deck, 3);

		PlayerProcessor.drawCard(p1, deck);
		
		System.out.println(p1.getCards());
		System.out.println(p2.getCards());
		System.out.println(p3.getCards());
		
		assertEquals(4, p1.getCards().size());
		assertEquals(3, p2.getCards().size());
		assertEquals(3, p3.getCards().size());
	}
	
	@Test
	@Order(4)
	void player1SkipPlayer2Test() {

		players.add(p1);
		players.add(p2);
		players.add(p3);

		List<Player> unskippedPlayers = Arrays.asList(p1, p3);
		List<Player> unskippedPlayers2 = new ArrayList<>();
		
		int idxCurrentPlayer;
		int idxNextPlayer;
		for (Player p : players) {
			idxCurrentPlayer = players.indexOf(p);

			if (idxCurrentPlayer == 0) {
				idxNextPlayer = ((idxCurrentPlayer + 1) > (players.size() - 1)) ? 0 : idxCurrentPlayer + 1;
				players.get(idxNextPlayer).setSkipped(true);
			}

		}
		
		for (Player p : players) {
			if(!p.isSkipped())
				unskippedPlayers2.add(p);
		}
		
		assertEquals(unskippedPlayers, unskippedPlayers2);
	}
	
	@Test
	@Order(5)
	void player2SkipPlayer3Test() {

		players.add(p1);
		players.add(p2);
		players.add(p3);

		List<Player> unskippedPlayers = Arrays.asList(p1, p2);
		List<Player> unskippedPlayers2 = new ArrayList<>();
		
		int idxCurrentPlayer;
		int idxNextPlayer;
		for (Player p : players) {
			idxCurrentPlayer = players.indexOf(p);

			if (idxCurrentPlayer == 1) {
				idxNextPlayer = ((idxCurrentPlayer + 1) > (players.size() - 1)) ? 0 : idxCurrentPlayer + 1;
				players.get(idxNextPlayer).setSkipped(true);
			}

		}
		
		for (Player p : players) {
			if(!p.isSkipped())
				unskippedPlayers2.add(p);
		}
		
		assertEquals(unskippedPlayers, unskippedPlayers2);
	}
	
	@Test
	@Order(6)
	void player3SkipPlayer1Test() {

		players.add(p1);
		players.add(p2);
		players.add(p3);

		List<Player> unskippedPlayers = Arrays.asList(p2, p3);
		List<Player> unskippedPlayers2 = new ArrayList<>();
		
		int idxCurrentPlayer;
		int idxNextPlayer;
		for (Player p : players) {
			idxCurrentPlayer = players.indexOf(p);

			if (idxCurrentPlayer == 2) {
				idxNextPlayer = ((idxCurrentPlayer + 1) > (players.size() - 1)) ? 0 : idxCurrentPlayer + 1;
				players.get(idxNextPlayer).setSkipped(true);
				System.out.println(players.get(idxNextPlayer));
			}

		}
		
		for (Player p : players) {
			if(!p.isSkipped())
				unskippedPlayers2.add(p);
		}
		
		assertEquals(unskippedPlayers, unskippedPlayers2);
	}
	
}
