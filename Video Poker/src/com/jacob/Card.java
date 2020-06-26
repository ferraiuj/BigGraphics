package com.jacob;

public class Card {

	public int suit;
	public int card;
	public boolean inHand;
	public Card(int card, int suit) {
		this.suit = suit;
		this.card = card;
	}
	public void setinHand(boolean inHand) {
		this.inHand = inHand;
	}

	public void setCard() {

	}

	public void setSuit() {

	}

	public String getCard() {
		String cardName = null;
		if(card == 0) {
			return cardName = "Ace";
		}else if(card == 1) {
			return cardName = "Two";
		}else if(card == 2) {
			return cardName = "Three";
		}else if(card == 3) {
			return cardName = "Four";
		}else if(card == 4) {
			return cardName = "Five";
		}else if(card == 5) {
			return cardName = "Six";
		}else if(card == 6) {
			return cardName = "Seven";
		}else if(card == 7) {
			return cardName = "Eight";
		}else if(card == 8) {
			return cardName = "Nine";
		}else if(card == 9) {
			return cardName = "Ten";
		}else if(card == 10) {
			return cardName = "Jack";
		}else if(card == 11) {
			return cardName = "Queen";
		}else if(card == 12) {
			return cardName = "King";
		}
		return cardName;
	}

	public String getSuitName() {
		String suitName = null;
		if(suit == 0) {
			return suitName = "Spade";
		}else if(suit == 1) {
			return suitName = "Heart";
		}else if(suit == 2) {
			return suitName = "Club";
		}else if(suit == 3) {
			return suitName = "Diamond";
		}
		return suitName;
	}
}
