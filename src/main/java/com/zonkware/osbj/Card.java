package com.zonkware.osbj;

/**
 * The Card has little functionality beyond determining a numeric value
 * and overriding the equals method for simple comparisons. It includes
 * attributes for setting a suit, rank and whether or not it is face up
 * on the game table.
 * @author Mike Chandler
 *
 */
public class Card {
	
	private String suit;
	private String rank;
	private boolean faceUp = true;
	
	public Card() {}
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	/**
	 * Used to retrieve the value of the card which is critical in determining a player's total hand.
	 * @return A numeric value based on the card's rank, regardless of the suit.
	 */
	public int getValue() {
		if (rank.toUpperCase().equals("J") || rank.toUpperCase().equals("Q") || rank.toUpperCase().equals("K")) {
			return 10;
		} else if (rank.toUpperCase().equals("A")) {
			return 11;
		} else {
			return Integer.parseInt(rank);
		}
	}
	
	/**
	 * A simple override of the equals method for comparing cards. This method is used behind
	 * the scenes to remove cards from a re-initialized deck when those cards are currently in
	 * play.
	 */
	@Override
	public boolean equals(Object obj) {
		Card compareCard = (Card) obj;
		
		return (compareCard.getSuit().equals(this.getSuit()) && 
				compareCard.getRank().equals(this.getRank()));
	}
	
	@Override
	public String toString() {
		return getRank() + " of " + getSuit();
	}
	
}
