package com.monstarlab.JavaExercise2;

import java.util.Scanner;

public class CardTest {

	private static int cardCounter = 0;

	private static Deck deck;

	private static Player player;
	private static Player dealer;

	private static final Integer ONE = 1;
	private static final Integer TEN = 10;
	private static final Integer FIFTEEN = 15;

	private static final Float PLAYER_INITIAL_MONEY = 100.00f;
	public static final String PLAYER_NAME = "PLAYER";
	public static final String DEALER_NAME = "DEALER";

	public static final String OPTION_BOOM = "BOOM";
	public static final String OPTION_PANES = "PANES";

	private static final Integer PLAYER_LIMIT = 25;
	private static final Integer DEALER_LIMIT = 16;

	public static void main(String[] args) {
		Boolean done = false;
		Scanner sc = new Scanner(System.in);
		String input = null;

		deck = new Deck();

		// Create the deck
		populateDeck();
		populateJacks();
		populateQueens();
		populateKings();
		populateAces();

		// deck.printDeck();

		// The player will be given a pot money of 100 pesos.
		player = new Player();
		player.setMoney(new Money(PLAYER_INITIAL_MONEY));
		player.setType(PLAYER_NAME);
		player.setIsOverWinningNumber(false);
		player.setWinningNumber(25);

		dealer = new Player();
		dealer.setType(DEALER_NAME);
		dealer.setIsOverWinningNumber(false);
		dealer.setWinningNumber(16);

		while (!done) {

			if (player.getHand() == null || player.getHand().size() > 0) {
				System.out.println("===CLEARING PLAYER'S HAND===");
				player.getHand().clear();

			}
			if (dealer.getHand() == null || dealer.getHand().size() > 0) {
				System.out.println("===CLEARING DEALER'S HAND===");
				dealer.getHand().clear();

			}

			System.out.println("CURRENT PLAYER MONEY IS " + player.getMoney().getValue());

			// while the player's money is still non zero
			if (player.getMoney().getValue() <= 0) {
				System.out.println("GAME OVER YOU CAN NO LONGER PLAY BECAUSE YOU DO NOT HAVE ENOUGH MONEY.");
				done = true;
				break;
			}

			// Read the bet of the player
			System.out.println("How much would you like to bet?");
			input = sc.nextLine();

			String str = input;
			float result = 0.0f;

			try {
				result = Float.parseFloat(str);

			} catch (NumberFormatException nfe) {
				// ask the user to try again
				done = false;
				System.out.println("The input " + result + " isn't a float or integer. Try again");
				continue;
			}

			if (!(result <= player.getMoney().getValue())) {
				done = false;
				System.out.println("The input " + result + " can't be greater than current player money. Try again");
				continue;
			}

			if (!(result > 0)) {
				done = false;
				System.out.println("The input " + result + " should be greater than 0. Try again");
				continue;
			}

			Money bet = new Money(result);
			player.setBet(bet);

			System.out.println("========= DEALER NOW DEALING =========");
			// Give the player 2 cards
			dealInitialCards(player);

			// Give the dealer 2 cards
			dealInitialCards(dealer);

			System.out.println("ROUND 1! FIGHT!");
			// Note that the dealer wins on a tie
			if (dealer.wonAlready()) {
				done = true;
				break;
			}
			if (player.wonAlready()) {
				done = true;
				break;
			}

			// Player has the option to BOOM OR PANES

			System.out.println("ROUND 2! FIGHT!");

			// randomize a card to show the player

			int lower = 0;
			int higher = 1;

			Card randomCard = dealer.getHand().get((int) (Math.random() * (higher - lower)) + lower);

			System.out.println("One of the cards of the dealer is " + randomCard.toString());

			String reply = null;
			Boolean isDone = false;

			while (!isDone) {

				if (PLAYER_LIMIT.intValue() < player.computeHandTotal().intValue()) {
					System.out.println("GAME OVER YOU WENT OVER YOUR LIMIT OF " + PLAYER_LIMIT);
					isDone = true;
					done = true;
					break;
				}

				System.out.println("YOU AREN'T ABOVE THE LIMIT, YOU CAN BOOM OR PANES: ");
				reply = sc.nextLine();

				switch (reply) {
				case OPTION_BOOM:
					System.out.println("Processing BOOM...");

					Card card = null;
					// Give a card to the player
					while (card == null) {
						card = deck.getCardFromDeck((int) Math.floor(Math.random() * 51) + 1);
					}
					player.getHand().add(card);
					System.out.println(card.toString());

					Integer newTotal = player.computeHandTotal();
					System.out.println("PLAYER TOTAL IS " + newTotal);

					// there is a possibility that the user will go over 25. In
					// this case, the user loses and the game is over.
					if (PLAYER_LIMIT.intValue() < newTotal.intValue()) {
						continue;
					}
					break;

				case OPTION_PANES:
					System.out.println("Processing PANES...");
					isDone = true;
					break;
				default:
					isDone = false;
					continue;
				}

			}

			// Dealer has the option to BOOM OR PANES

			isDone = false;

			do {
				// The dealer can get more cards as long as the value of the
				// dealer's hand is less than or equal to 16

				if (done.equals(true)) {
					break;
				}

				if (DEALER_LIMIT.intValue() < dealer.computeHandTotal()) {
					System.out.println("FYI - DEALER IS OVER THE LIMIT OF " + DEALER_LIMIT);
					isDone = true;
					System.out.println("FYI - THE WINNER WILL NOW BE DETERMINED");
					conclude();
					break;
				}

				else {
					System.out.println("========= DEALER NOW DRAWING =========");

					Card card = null;
					// Give the 1st card to the dealer
					while (card == null) {
						card = deck.getCardFromDeck((int) Math.floor(Math.random() * 51) + 1);
					}
					dealer.getHand().add(card);
					System.out.println(card.toString());

					Integer newTotal = dealer.computeHandTotal();
					System.out.println("DEALER TOTAL IS " + newTotal);
				}

			} while (!isDone && !done);

		}

		sc.close();

	}

	private static void populateDeck() {

		for (int i = 2; i < 11; i++) {

			populateCard(Integer.toString(i), i, i);

		}

	}

	private static void populateJacks() {
		populateCard("Jack", TEN, TEN);
	}

	private static void populateQueens() {
		populateCard("Queen", TEN, TEN);
	}

	private static void populateKings() {
		populateCard("King", TEN, TEN);
	}

	private static void populateAces() {
		populateCard("Ace", ONE, FIFTEEN);
	}

	private static void populateCard(String name, Integer value, Integer specialValue) {
		Card cardClover = new Card();
		cardClover.setId(++cardCounter);
		cardClover.setName(name);
		cardClover.setValue(value);
		cardClover.setSpecialValue(specialValue);
		cardClover.setSuit("Cloves");
		cardClover.setTaken(false);

		Card cardSpade = new Card();
		cardSpade.setId(++cardCounter);
		cardSpade.setName(name);
		cardSpade.setValue(value);
		cardSpade.setSpecialValue(specialValue);
		cardSpade.setSuit("Spades");
		cardSpade.setTaken(false);

		Card cardHeart = new Card();
		cardHeart.setId(++cardCounter);
		cardHeart.setName(name);
		cardHeart.setValue(value);
		cardHeart.setSpecialValue(specialValue);
		cardHeart.setSuit("Hearts");
		cardHeart.setTaken(false);

		Card cardDiamond = new Card();
		cardDiamond.setId(++cardCounter);
		cardDiamond.setName(name);
		cardDiamond.setValue(value);
		cardDiamond.setSpecialValue(specialValue);
		cardDiamond.setSuit("Diamonds");
		cardDiamond.setTaken(false);

		deck.addCard(cardClover);
		deck.addCard(cardSpade);
		deck.addCard(cardHeart);
		deck.addCard(cardDiamond);

	}

	private static void dealInitialCards(Player participant) {
		Card card = null;
		// Give the 1st card to the player
		while (card == null) {
			card = deck.getCardFromDeck((int) Math.floor(Math.random() * 51) + 1);
		}
		participant.getHand().add(card);
		// System.out.println(card.toString());

		card = null;
		// Give the 2nd card to the player
		while (card == null) {
			card = deck.getCardFromDeck((int) Math.floor(Math.random() * 51) + 1);
		}
		participant.getHand().add(card);
		// System.out.println(card.toString());

		participant.setHand(participant.getHand());
	}

	private static void conclude() {
		Integer dealerTotal = dealer.computeHandTotal();
		Integer playerTotal = player.computeHandTotal();

		if (PLAYER_LIMIT.intValue() < dealerTotal) {
			System.out.println("THE" + PLAYER_NAME + " WINS");
			player.setMoney(new Money(player.getBet().getValue() + player.getMoney().getValue()));

		} else if (dealerTotal >= playerTotal) {
			System.out.println("THE" + DEALER_NAME + " WINS");
			player.setMoney(new Money(player.getMoney().getValue() - player.getBet().getValue()));

		} else {
			System.out.println("THE" + PLAYER_NAME + " WINS");
			player.setMoney(new Money(player.getBet().getValue() + player.getMoney().getValue()));

		}
	}
}
