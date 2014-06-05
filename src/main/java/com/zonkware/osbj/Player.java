package com.zonkware.osbj;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private float money;
	private List<Card> hand;
	private boolean standing;
	
	public Player() {
		newGame();
	}
	
	public Player(float money) {
		this();
		this.money = money;
	}
	
	public Player newGame() {
		hand = new ArrayList<Card>();
		setStanding(false);
		return this;
	}
	
	public float getMoney() {
		return money;
	}
	
	public void setMoney(float money) {
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
	
	public void stand() {
		setStanding(true);
	}

	public void take(Card card) {
		hand.add(card);
	}
	
	public int totalHand() {
		ArrayList<Card> aces = new ArrayList<Card>();
		int total = 0;
		
		for (Card card : hand) {
			if (card.getRank().equals("A")) {
				aces.add(card);
				continue; // hold calculation of aces
			}
			
			total += card.getValue();
		}
		
		if (aces.size() > 0) {
			for (Card card : aces) {
				int bustTest = total + card.getValue();
				
				if (bustTest > 21) {
					total += 1;
				} else {
					total += card.getValue();
				}
			}
		}
		
		aces = null;
		
		return total;
	}
	
	public boolean hasBlackJack() {
		if (hand.size() == 2 && totalHand() == 21) {
			return true;
		}
		
		return false;
	}
	
	public boolean isBusted() {
		return (totalHand() > 21) ? true : false;
	}
	
	public boolean beats(Player player) {
		return (!isBusted() && totalHand() > player.totalHand()) ? true : false;
	}
	
}
