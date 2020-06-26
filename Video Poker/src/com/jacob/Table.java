package com.jacob;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Table {
	public Random random;
	public Card card;
	public UserInterface gui;
	public List<Card> deck = new ArrayList<Card>();
	public List<Card> hand = new ArrayList<Card>();
	public List<Card> shuffle = new ArrayList<Card>(52);
	// 52 cards every 13 is a new suit and
	// public List<Integer> deck = new ArrayList<Integer>();

	// public List<Integer> hearts = new ArrayList<Integer>();
	// public List<Integer> clubs = new ArrayList<Integer>();
	// public List<Integer> diamonds = new ArrayList<Integer>();
	// public List<Integer> spades = new ArrayList<Integer>();

	// public List<Integer> suits = new ArrayList<Integer>();

	public Table() {

		random = new Random();
		gui = new UserInterface();
	}

	public void populateDeck() {
		for (int i = 0; i < 52; i++) {
			card = new Card(i % 13, i % 4);
			deck.add(card);
		}
	}

	public void drawHands(int amt) {
		for (int i = 0; i < amt; i++) {
			setHand();
			checkHand();
			returnHand();
			
			System.out.println("Hand number = " + i + "\n");
		}
	}
	
	public void setHand() {

		for (int i = 0; i < 5; i++) {
			int card = random.nextInt(52);
			// this is going to give me a random card object from my deck list

			while (deck.get(card).inHand == true) {
				// checks to see if the card in the deck is already in use
				card = random.nextInt(52 - i);
			}
			hand.add(deck.get(card));
			hand.get(i).setinHand(true);
			System.out.print(hand.get(i).getCard() + " ");
			System.out.println( hand.get(i).getSuitName());
		}
		
	}
	public void returnHand() {
		for (int i = 0; i < 5; i++) {
			hand.get(i).setinHand(false);	
		}
		hand.clear();
	}
	public void checkHand() {
		for(int i = 0; i < 5; i++) {
			gui.showHand(hand.get(i));
		}
		
		
//		for (int i = 0; i < 5; i++) {
//			//flush(hand.get(i));
//			// System.out.println();
//		}
	}

//	public void shuffleDeck() {
//		for (int i = 0; i < 52; i++) {
//			shuffle.add(deck.get(i).getCard());
//			shuffle.add(deck.get(i).getCard());
//			deck.get(i).setCard();
//			deck.get(i).setCard();			
//		}
//	}

	public void flush(Card card) {

	}

	public void royalFlush() {

	}

	public static void main(String[] args) {
		Table sim = new Table();
		
		sim.populateDeck();
		sim.drawHands(1000);

		// sim.setHand();
//		for (int i = 0; i < 10; i++) {
//			sim.currentHand();
//			sim.checkCard();
//			sim.suits.clear();
//			sim.cards.clear();
//		}
	}

}
