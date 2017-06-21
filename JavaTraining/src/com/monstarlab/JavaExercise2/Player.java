package com.monstarlab.JavaExercise2;

import java.util.ArrayList;

public class Player {
	private Money money;

	private Money bet;

	private ArrayList<Card> hand;

	private String type;

	private Boolean isOverWinningNumber;

	private Integer winningNumber;

	private final Integer ROUND1_WINNING_NUMBER = 25;
	private final Integer FIFTEEN = 15;

	private final String ACE_TEXT = "Ace";

	public Player() {
		hand = new ArrayList<Card>();
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Money getBet() {
		return bet;
	}

	public void setBet(Money bet) {
		this.bet = bet;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsOverWinningNumber() {
		return isOverWinningNumber;
	}

	public Integer getWinningNumber() {
		return winningNumber;
	}

	public void setWinningNumber(Integer winningNumber) {
		this.winningNumber = winningNumber;
	}

	public void setIsOverWinningNumber(Boolean isOverWinningNumber) {
		this.isOverWinningNumber = isOverWinningNumber;
	}

	public Integer computeHandTotal() {

		Integer total = 0;

		// get total of non ace cards
		for (Card card : this.getHand()) {

			switch (card.getName()) {
			case ACE_TEXT:
				// do nothing for ace cards for now
				break;
			default:
				total = total + card.getValue();
				break;
			}

		}

		// get total of ace cards
		for (Card card : this.getHand()) {

			if (ACE_TEXT.equals(card.getName()) && ((total + FIFTEEN) > this.getWinningNumber())) {

				total = total + card.getValue();

			} else if (ACE_TEXT.equals(card.getName()) && ((total + FIFTEEN) < this.getWinningNumber())) {
				total = total + card.getSpecialValue();

			} else {
				// do nothing for non ace cards for now
			}

		}

		switch (this.getType()) {
		case CardTest.DEALER_NAME:
			// System.out.println("The " + CardTest.DEALER_NAME + "'s Hand Total
			// is " + total);
			break;
		case CardTest.PLAYER_NAME:
			System.out.println("The " + CardTest.PLAYER_NAME + "'s Hand Total is " + total);
			break;
		}

		if (total >= this.getWinningNumber()) {
			this.setIsOverWinningNumber(true);
		} else {
			this.setIsOverWinningNumber(false);
		}
		return total;
	}

	public Boolean wonAlready() {

		Integer total = this.computeHandTotal();

		if (ROUND1_WINNING_NUMBER.equals(total)) {
			if (CardTest.DEALER_NAME.equals(this.getType())) {
				System.out.println(CardTest.DEALER_NAME + " HAS WON");
				return true;
			} else if (CardTest.PLAYER_NAME.equals(this.getType())) {
				System.out.println(CardTest.PLAYER_NAME + " HAS WON");
				return true;
			} else {
				System.out.println("this.getType() in the wonAlready method has a serious error");
				System.out.println(this.getType());
			}

		}

		return false;
	}
}
