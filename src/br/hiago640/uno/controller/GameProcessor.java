package br.hiago640.uno.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.CardColor;
import br.hiago640.uno.model.CardType;
import br.hiago640.uno.model.Player;

public class GameProcessor {

	private Scanner scan = new Scanner(System.in);

	private List<Card> cards;
	private Deque<Card> discardedCards = new ArrayDeque<>();
	private LinkedList<Player> players = new LinkedList<>();

	public GameProcessor() {
		createDeckCards();
		createPlayers();
		dealCards();

		initTurn();
	}

	private void initTurn() {
		
		boolean isFinished;
		do {

			System.out.println("Turns: " + players);

			for (Player player : players) {
				if (player.isSkipped()) {
					System.out.println("Your turn: " + player.getName());
					System.out.println("Your turn is Skipped!");
					player.setSkipped(false);
					break;
				}

				System.out.println("\n\n==========================================================");
				makePlay(player);
			}

			isFinished = players.stream().anyMatch(p -> p.getCards().isEmpty());

		} while (!isFinished);

	}

	private void makePlay(Player player) {
		Card chosenCard = null;

		Card lastCard = CardProcessor.getLastCardDeck(discardedCards);
		System.out.println("Your turn: " + player.getName());
		System.out.println("Last Card played: " + lastCard);

		do {
			player.showPlayerHand();
			System.out.print("\nChoose a Card to throw (index): ");

			int indexCard = Integer.parseInt(scan.nextLine()) - 1;
			chosenCard = player.getCards().get(indexCard);

		} while (!validCard(chosenCard));

		player.getCards().remove(chosenCard);
		PlayerProcessor.discardedCards(chosenCard, discardedCards);

		if(!CardType.NUMBER.equals(chosenCard.getType()))
			cardTypeActions(player, chosenCard);
		
	}

	private void cardTypeActions(Player player, Card chosenCard) {
		if (CardType.SKIP_CARD.equals(chosenCard.getType())) {
			int idxCurrentPlayer = players.indexOf(player);
			int idxNextPlayer = (idxCurrentPlayer + 1 > players.size()) ? 0 : idxCurrentPlayer + 1;

			players.get(idxNextPlayer).setSkipped(true);
		}
	}

	private boolean validCard(Card chosenCard) {
		Card lastCardDeck = CardProcessor.getLastCardDeck(discardedCards);

		boolean isSameValues = lastCardDeck.getValue().equals(chosenCard.getValue());
		boolean isSameColors = false;
		boolean isWildCards = CardType.getCardsNotNumberType().contains(chosenCard.getType());
		boolean lastCardDeckIsWildCard = CardType.getWildCardsType().contains(lastCardDeck.getType());

		if (!isWildCards || !lastCardDeckIsWildCard) {
			isSameColors = lastCardDeck.getColor().equals(chosenCard.getColor());
		}

		return (isSameValues || isSameColors || isWildCards);
	}

	private void dealCards() {
		for (Player player : players)
			PlayerProcessor.drawCards(player, cards, 7);

		// show a first card
		do {
			moveToDiscardedCards(cards.remove(0), discardedCards);
		} while (!discardedCards.getLast().getType().equals(CardType.NUMBER));

	}

	private void createDeckCards() {
		cards = CardProcessor.createDeck();
		Collections.shuffle(cards);
	}

	private void createPlayers() {
		System.out.print("How many Players will play?: ");
		int amountPlayers = Integer.parseInt(scan.nextLine());

		String name;
		for (int i = 1; i <= amountPlayers; i++) {
			System.out.printf("\nPlayer Nº" + i);
			System.out.print("\nEnter your name: ");
			name = scan.nextLine();

			players.add(new Player(name));
		}
	}

	public static void moveToDiscardedCards(Card card, Deque<Card> discardedCard) {
		discardedCard.add(card);
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
