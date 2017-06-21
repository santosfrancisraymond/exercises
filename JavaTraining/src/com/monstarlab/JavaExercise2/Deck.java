package com.monstarlab.JavaExercise2;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void removeCard(Card card) {
		this.cards.remove(card);
	}

	public Card getCardFromDeck(Integer number) {

		for (Card card : cards) {
			if ((card.getTaken().equals(false)) && (card.getId().equals(number))) {
				card.setTaken(true);
				return card;
			}

			continue;
		}

		//System.out.println(number + " has aleady been drawn. Draw again.");

		// TODO Infinite loop if all cards have been drawn already
		return null;

	}

	public void printDeck() {

		for (Card card : this.getCards()) {
			System.out.println(card.toString());
		}

		System.out.println("Size is : " + this.getCards().size());
	}
}
