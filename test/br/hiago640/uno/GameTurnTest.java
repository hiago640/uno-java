package br.hiago640.uno;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.hiago640.uno.controller.CardProcessor;
import br.hiago640.uno.controller.PlayerProcessor;
import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.CardColor;
import br.hiago640.uno.model.CardType;
import br.hiago640.uno.model.Player;

class GameTurnTest {

	private List<Card> deck;
	private Player p1 = new Player("Player1");
	private Player p2 = new Player("Player2");
	private Player p3 = new Player("Player3");
	private Stack<Card> discardedCards = new Stack<>();

	private Card skipCard = new Card(CardType.SKIP_CARD.getValue(), CardColor.RED, CardType.SKIP_CARD);
	private List<Player> players = new LinkedList<>();

	@BeforeEach
	void initialize() {
		deck = CardProcessor.createDeck();

		PlayerProcessor.drawCards(p1, deck, 1);
		p1.getCards().add(skipCard);

		PlayerProcessor.drawCards(p2, deck, 1);
		p2.getCards().add(skipCard);
		
		PlayerProcessor.drawCards(p3, deck, 1);
		p3.getCards().add(skipCard);

		players.add(p1);
		players.add(p2);
		players.add(p3);

		discardedCards.add(deck.get(0));
	}

	@Test
	@Order(1)
	void player1SkipPlayer2Test() {
		System.out.println("\n=============== player1SkipPlayer2Test ===============");

		for (Player player : players) {
			if (player.isSkipped()) {
				System.out.println("\n\nSkipped Player: " + player.getName() + "\n");
				player.setSkipped(false);
				continue;
			}

			makePlay(player, p1);
		}
		System.out.println("\n======================================================\n");
	}

	@Test
	@Order(2)
	void player2SkipPlayer3Test() {
		System.out.println("\n=============== player2SkipPlayer3Test ===============");

		for (Player player : players) {
			if (player.isSkipped()) {
				System.out.println("\n\nSkipped Player: " + player.getName() + "\n");
				player.setSkipped(false);
				continue;
			}

			makePlay(player, p2);
		}
		System.out.println("\n======================================================\n");
	}
	
	@Test
	@Order(3)
	void player3SkipPlayer1Test() {
		System.out.println("\n=============== player2SkipPlayer3Test ===============");

		for (Player player : players) {
			if (player.isSkipped()) {
				System.out.println("\n\nSkipped Player: " + player.getName() + "\n");
				player.setSkipped(false);
				continue;
			}

			makePlay(player, p2);
		}
		System.out.println("\n======================================================\n");
	}
	
	private void makePlay(Player player, Player whoPlaysTheSkippingCard) {
		System.out.println("Cards on the table" + discardedCards + "\n");

		Card lastCard = CardProcessor.getLastCardStackDeck(discardedCards);
		System.out.println("Your turn: " + player.getName());
		System.out.println("Last Card played: " + lastCard);

		player.showPlayerHand();

		int indexCard = player.equals(whoPlaysTheSkippingCard) ? 1 : 0;
		Card chosenCard = player.getCards().get(indexCard);
		System.out.print("Playing Card... " + chosenCard);

		player.getCards().remove(chosenCard);
		discardedCards.add(chosenCard);

		cardTypeActions(player, chosenCard);
	}

	private void cardTypeActions(Player player, Card chosenCard) {
		if (CardType.SKIP_CARD.equals(chosenCard.getType())) {
			int idxCurrentPlayer = players.indexOf(player);
			int idxNextPlayer = (idxCurrentPlayer + 1 > players.size()) ? 0 : idxCurrentPlayer + 1;

			players.get(idxNextPlayer).setSkipped(true);
		}
	}

}
