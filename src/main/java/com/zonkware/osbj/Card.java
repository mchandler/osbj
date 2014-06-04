package com.zonkware.osbj;

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

	public int getValue() {
		switch (rank) {
		
		case "J":
		case "Q":	
		case "K":
			return 10;
			
		case "A":
			return 11;
		default:
			return Integer.parseInt(rank);
		}
	}
	
}
