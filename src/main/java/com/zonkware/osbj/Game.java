package com.zonkware.osbj;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Game class is almost like a typical service class. Rather than interacting
 * with the key classes directly, such as Player, Dealer and Card, software
 * developers should expect to leverage many of the methods in this class.
 * 
 * @author Mike Chandler
 *
 */
public class Game {
	
	private Player player;
	private Dealer dealer;
	private ArrayList<Card> deck;
	private int pot;
	
	/**
	 * Constructor used to create a brand new game. The Player and Dealer
	 * are initialized automatically.
	 * @param houseBank Integer representing the starting value of the house bank
	 */
	public Game(int houseBank) {
		this(new Player(), houseBank);
	}
	
	/**
	 * Constructor used to create a brand new game. The Player is accepted
	 * as an argument which assumes it was already initialized outside of this
	 * class, while the Dealer is initialized automatically.
	 * @param player The initialized Player instance
	 * @param houseBank Integer representing the starting value of the house bank
	 */
	public Game(Player player, int houseBank) {
		this.setPlayer(player.newGame());
		dealer = new Dealer();
		dealer.setMoney(houseBank);
		
		initializeNewDeck();
	}
	
	/**
	 * Constructor used to create a game with an existing Player instance,
	 * Dealer instance and deck of cards. This could be useful if you are
	 * developing a stateless application.
	 * @param player The initialized Player instance
	 * @param dealer The initialized Dealer instance
	 * @param deck An ArrayList of the Card class
	 */
	public Game(Player player, Dealer dealer, ArrayList<Card> deck) {
		this.setPlayer(player.newGame());
		this.dealer = dealer;
		getDealer().newGame();
		this.deck = deck;
	}
	
	/**
	 * In a stateful application, this method can be invoked to clear out
	 * both the Player and Dealer hands, reset their standing values and prepare
	 * them for a new game.
	 */
	public void newGame() {
		getPlayer().newGame();
		getDealer().newGame();
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
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	/**
	 * Deals two cards to the Player, both face-up, and two cards to the Dealer,
	 * one face-up and another face-down.
	 * @return Returns true if either player has Blackjack after the initial cards are dealt.
	 */
	public boolean startingDeal() {
		dealCardTo(dealer, true);
		dealCardTo(player, true);
		dealCardTo(dealer, false);
		dealCardTo(player, true);
		
		return (dealer.hasBlackJack() || player.hasBlackJack());
	}
	
	/**
	 * Deals a card from the deck to the player specified
	 * @param player An instance of a Player or a Dealer
	 * @return an instance of the Card that was dealt
	 */
	public Card dealCardTo(Player player) {
		return dealCardTo(player, true);
	}
	
	/**
	 * Deals a card from the deck to the player specified.
	 * @param player An instance of a Player or a Dealer
	 * @param faceUp Set to TRUE if dealing a card that is face-up
	 * @return an instance of the Card that was dealt
	 */
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
	
	/**
	 * Takes the specified bet amount from the specified player's
	 * bank or wallet and places it in the current Game pot.
	 * @param player An instance of a Player or a Dealer
	 * @param bet The bet amount as an integer
	 */
	public void takeBetFrom(Player player, int bet) {
		pot = pot + player.bet(bet);
	}
	
	/**
	 * Takes the specified bet amount from the specified player's
	 * bank or wallet and places it in the current Game pot.
	 * @param player An instance of a Player or a Dealer
	 * @param bet The bet amount as a string
	 */
	public void takeBetFrom(Player player, String bet) {
		takeBetFrom(player, Integer.parseInt(bet));
	}
	
	/**
	 * If your game rules determine that a winner exists, invoking this
	 * method will take the money in the current Game pot and award it
	 * to the Player or Dealer specified.
	 * @param player An instance of a Player or a Dealer
	 */
	public void reward(Player player) {
		player.reward(pot);
		pot = 0;
	}
	
	/**
	 * If your game rules determine that the match is a push, invoking
	 * this method will take the money in the current Game pot and award
	 * it equally to both the Player and the Dealer.
	 */
	public void push() {
		int reward = pot / 2;
		player.reward(reward);
		dealer.reward(reward);
		pot = 0;
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
		initializeNewDeck();
		
		for (Card card : player.getHand()) {
			deck.remove(card);
		}
		
		for (Card card : dealer.getHand()) {
			deck.remove(card);
		}
	}
	
}
