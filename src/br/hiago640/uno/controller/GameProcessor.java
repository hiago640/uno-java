package br.hiago640.uno.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import br.hiago640.uno.model.Card;
import br.hiago640.uno.model.CardType;
import br.hiago640.uno.model.Player;

public class GameProcessor {

	private Scanner scan = new Scanner(System.in);

	private List<Card> cards;
	private Deque<Card> discardedCards = new ArrayDeque<>();
	private Set<Player> players = new LinkedHashSet<>();
	private boolean isFinished;

	public GameProcessor() throws Exception {

		createDeckCards();
		createPlayers();

		dealCards();

		initGame();

		System.out.println("Cartas no baralho para comprar: " + cards.size());
		System.out.println("Cartas descartadas: " + discardedCards.size());

		List<Player> playersList = new ArrayList<>();
		playersList.addAll(players);
		Card chosenCard = null;
		do {
			System.out.println("Turns: " + playersList);

			for (Player player : playersList) {
				System.out.println(CardProcessor.getLastCardDeck(discardedCards));

				System.out.println("Your turn: " + player.getName());

				do {
					player.showPlayerHand();
					System.out.print("\nChoose a Card to throw (index): ");

					int indexCard = Integer.parseInt(scan.nextLine()) - 1;
					chosenCard = player.getCards().get(indexCard);

				} while (!validCard(chosenCard));

				PlayerProcessor.discardedCards(chosenCard, discardedCards);
				
				System.out.println("Fim de jogada");
			}

//			Collections.reverse(playersList);

//			isFinished = true;
		} while (!isFinished);

	}

	private boolean validCard(Card chosenCard) {
		Card lastCardDeck = CardProcessor.getLastCardDeck(discardedCards);

		boolean isSameValues = lastCardDeck.getValue().equals(chosenCard.getValue());
		boolean isSameColors = lastCardDeck.getColor().equals(chosenCard.getColor());

		return (isSameValues || isSameColors);
	}

	private void initGame() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("\nDealing Cards ...");

	}

	private void dealCards() {
		for (Player player : players)
			PlayerProcessor.drawCards(player, cards, 7);

		// show a first card
		Card card = null;
		do {
			card = cards.remove(0);

			moveToDiscardedCards(card, discardedCards);
		} while (!card.getType().equals(CardType.NUMBER));

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

	public static void moveToDiscardedCards(Card card, Collection<Card> discardedCard) {
		discardedCard.add(card);
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
