package br.hiago640.uno.controller;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListCellRenderer;

import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.CardColor;
import br.hiago640.uno.model.CardType;
import br.hiago640.uno.model.Player;

public class GameProcessor {

	private Scanner scan = new Scanner(System.in);

	private List<Card> cards;
	private Deque<Card> discardedCards = new ArrayDeque<>();
	private LinkedList<Player> players = new LinkedList<>();
	
	private CardColor tableColor;

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
				
				System.out.println("Table color: " + tableColor);
				
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

		System.out.println(discardedCards);

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

		tableColor = chosenCard.getColor();
		
		if (!CardType.NUMBER.equals(chosenCard.getType()))
			cardTypeActions(player, chosenCard);

	}

	private void cardTypeActions(Player player, Card chosenCard) {

		int idxCurrentPlayer = players.indexOf(player);
		int idxNextPlayer = (idxCurrentPlayer + 1 >= players.size()) ? 0 : idxCurrentPlayer + 1;

		Player nextPlayer = players.get(idxNextPlayer);

		switch (chosenCard.getType()) {
		case SKIP_CARD: {
			nextPlayer.setSkipped(true);
			break;
		}
		case WILD_CARD: {

			break;
		}
		case DRAW_TWO_CARD: {

			break;
		}
		case WILD_DRAW_FOUR_CARD: {
			nextPlayer.setSkipped(true);

			PlayerProcessor.drawCards(nextPlayer, cards, 4);

			chooseColor();
			break;
		}
		case REVERSE_CARD: {

			break;
		}
		default:

		}
	}

	private void chooseColor() {
		System.out.println();
		
		for (CardColor color : CardColor.values())
			System.out.println(color.getColor());

		System.out.print("\nChoose a Color: ");
		int colorIndex = Integer.parseInt(scan.nextLine());
		
		tableColor = CardColor.values()[colorIndex - 1];
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
		moveToDiscardedCards(cards.remove(0), discardedCards);

		while (!discardedCards.getLast().getType().equals(CardType.NUMBER)) {
			moveToDiscardedCards(cards.remove(0), discardedCards);			
		}
		
		
		tableColor = discardedCards.getFirst().getColor();
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
		discardedCard.addFirst(card);
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
