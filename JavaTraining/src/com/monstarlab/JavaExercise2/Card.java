package com.monstarlab.JavaExercise2;

public class Card {

	Integer id;

	String name;

	Integer value;

	Integer specialValue;

	String suit;

	Boolean taken;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public Integer getSpecialValue() {
		return specialValue;
	}

	public void setSpecialValue(Integer specialValue) {
		this.specialValue = specialValue;
	}

	public Boolean getTaken() {
		return taken;
	}

	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	public String toString() {
		return this.getName() + " of " + this.getSuit();
	}

	public boolean equals(Card card) {
		if (this.toString().equals(card.toString())) {
			return true;
		}

		return false;
	}
}
