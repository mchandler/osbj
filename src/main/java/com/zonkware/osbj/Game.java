package com.zonkware.osbj;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
	private Player player;
	private Dealer dealer;
	private ArrayList<Card> deck;
	
	public Game(float houseBank) {
		this(new Player(), houseBank);
	}
	
	public Game(Player player, float houseBank) {
		this.setPlayer(player.newGame());
		dealer = new Dealer();
		dealer.setMoney(houseBank);
		
		initializeNewDeck();
	}
	
	public Game(Player player, Dealer dealer, ArrayList<Card> deck) {
		this.setPlayer(player.newGame());
		this.dealer = dealer;
		this.deck = deck;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Dealer getDealer() {
		return dealer;
	}

	public void startingDeal() {
		dealCardTo(dealer, true);
		dealCardTo(player, true);
		dealCardTo(dealer, false);
		dealCardTo(player, true);
	}
	
	public Card dealCardTo(Player player, boolean faceUp) {
		if (deck.size() == 0) {
			// handle shuffling a new deck and removing cards in play
		}
		
		Card card = deck.get(0);
		card.setFaceUp(faceUp);
		deck.remove(0);
		
		player.take(card);
		
		return card;
	}
	
	private void initializeNewDeck() {
		deck = shuffleNewDeck();
	}
	
	private ArrayList<Card> shuffleNewDeck() {
		String[] suits = {"spades", "hearts", "diamonds", "clubs"};
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K", "A"};
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for (String suit : suits) {
			for (String rank : ranks) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}
		
		Collections.shuffle(cards);
		
		return cards;
	}
	
}
