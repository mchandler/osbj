package com.zonkware.osbj;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
	private Player player;
	private Dealer dealer;
	private ArrayList<Card> deck;
	private int pot;
	
	public Game(int houseBank) {
		this(new Player(), houseBank);
	}
	
	public Game(Player player, int houseBank) {
		this.setPlayer(player.newGame());
		dealer = new Dealer();
		dealer.setMoney(houseBank);
		pot = 0;
		
		initializeNewDeck();
	}
	
	public Game(Player player, Dealer dealer, ArrayList<Card> deck) {
		this.setPlayer(player.newGame());
		this.dealer = dealer;
		this.deck = deck;
		pot = 0;
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
	
	public ArrayList<Card> getDeck() {
		return deck;
	}

	// starting deal returns true if either player has blackjack
	public boolean startingDeal() {
		dealCardTo(dealer, true);
		dealCardTo(player, true);
		dealCardTo(dealer, false);
		dealCardTo(player, true);
		
		return (dealer.hasBlackJack() || player.hasBlackJack());
	}
	
	public Card dealCardTo(Player player) {
		return dealCardTo(player, true);
	}
	
	public Card dealCardTo(Player player, boolean faceUp) {
		if (deck.size() == 0) {
			midPlayShuffle();
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
	
	private void midPlayShuffle() {
		System.out.println("Empty Deck: re-shuffling!");
		initializeNewDeck();
		
		for (Card card : player.getHand()) {
			deck.remove(card);
		}
		
		for (Card card : dealer.getHand()) {
			deck.remove(card);
		}
		System.out.println("Deck size: " + deck.size());
	}
	
}
