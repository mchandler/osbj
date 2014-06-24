package com.zonkware.osbj;

import java.util.ArrayList;
import java.util.List;

/**
 * This is all of the functionality related to managing the Player. Though these methods
 * are mostly public, the Game class should be considered the service that invokes them.
 * @author Mike Chandler
 * @see Game
 *
 */
public class Player {
	
	private int money;
	private List<Card> hand;
	private boolean standing;
	
	/**
	 * Creates an instance of a Player, initializes the Player's hand
	 * and sets up the object for a new game. NOTE: the wallet is NOT
	 * initialized here.
	 */
	public Player() {
		newGame();
	}
	
	/**
	 * Creates an instance of a Player, initializes the Player's hand
	 * and sets up the object for a new game. The Player's wallet is
	 * initialized with the amount of money specified in the argument.
	 * @param money An integer of the starting wallet amount for this Player.
	 */
	public Player(int money) {
		this();
		this.money = money;
	}
	
	/**
	 * Initializes the Player's hand in preparation for a new game.
	 * @return The Player
	 */
	public Player newGame() {
		hand = new ArrayList<Card>();
		setStanding(false);
		return this;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

	public List<Card> getHand() {
		return hand;
	}

	public boolean isStanding() {
		return standing;
	}

	public void setStanding(boolean standing) {
		this.standing = standing;
	}
	
	/**
	 * Sets a player's standing value to true.
	 */
	public void stand() {
		setStanding(true);
	}

	/**
	 * Adds the specified Card to the Player's hand.
	 * @param card An instance of a Card
	 */
	public void take(Card card) {
		hand.add(card);
	}
	
	/**
	 * Evalutes the player's hand and determines its total numeric value.
	 * @return The numeric value of the player's hand.
	 */
	public int totalHand() {
		ArrayList<Card> aces = new ArrayList<Card>();
		int total = 0;
		
		for (Card card : hand) {
			if (card.getRank().equals("A")) {
				aces.add(card);
				continue; // hold calculation of aces
			}
			
			total = total + card.getValue();
		}
		
		if (aces.size() > 0) {
			for (Card card : aces) {
				int bustTest = total + card.getValue();
				
				if (bustTest > 21) {
					total = total + 1;
				} else {
					total = total + card.getValue();
				}
			}
		}
		
		aces = null;
		
		return total;
	}
	
	/**
	 * Checks the player's hand to determine if they have Blackjack.
	 * @return TRUE if Blackjack, otherwise FALSE
	 */
	public boolean hasBlackJack() {
		if (hand.size() == 2 && totalHand() == 21) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks the player's hand to determine if they have busted.
	 * @return TRUE if busted, otherwise FALSE
	 */
	public boolean isBusted() {
		return (totalHand() > 21) ? true : false;
	}
	
	/**
	 * Evalutes this player against the player specified in the arguments and returns
	 * TRUE if this player's hand beats the other.
	 * @param player An instance of a player to be compared.
	 * @return TRUE if this player beats the other, otherwise returns FALSE
	 */
	public boolean beats(Player player) {
		boolean cardsBeaten = player.totalHand() > totalHand() && !player.isBusted();
		boolean isPush = player.totalHand() == totalHand();
		return (!isBusted() && !cardsBeaten && !isPush) ? true : false;
	}
	
	/**
	 * Removes a specified amount of money from the player's wallet, only if the player
	 * has the funds.
	 * @param bet An integer representing the bet amount
	 * @return Returns 0 if the player doesn't have enough money to support the bet.
	 */
	public int bet(int bet) {
		if (money >= bet) {
			money = money - bet;
			return bet;
		}
		
		return 0;
	}
	
	/**
	 * Places the specified amount into the player's wallet.
	 * @param pot The amount to be awarded as an integer
	 */
	public void reward(int pot) {
		money = money + pot;
	}
	
}
