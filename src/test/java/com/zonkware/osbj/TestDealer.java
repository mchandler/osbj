package com.zonkware.osbj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDealer {
	
	private Dealer dealer;
	
	@Before
	public void setUp() {
		dealer = new Dealer();
		dealer.setMoney(500);
	}
	
	@Test
	public void testTakeOverride() {
		dealer.stand();
		
		Card card = new Card("spades", "Q");
		dealer.take(card);
		
		Assert.assertFalse(dealer.isStanding());
	}
	
	@Test
	public void testCardTakingToWin() {
		Player player = cardTakingTestSetUp();
		
		while (dealer.competeWith(player) == false) {
			Card card = new Card("suit", "3");
			dealer.take(card);
		}
		
		Assert.assertTrue(dealer.totalHand() == 21); // dealer plays to win
		Assert.assertTrue(dealer.getHand().size() == 6); // dealer has 7 cards
	}
	
	@Test
	public void testPushHandling() {
		Player player = pushHandlingTestSetUp();
		
		Assert.assertFalse(dealer.competeWith(player));
		
		Card card = new Card("diamonds", "5");
		dealer.take(card);
		
		Assert.assertTrue(dealer.competeWith(player));
		Assert.assertTrue(dealer.isStanding());
	}
	
	@Test
	public void testRiskyPushHandling() {
		Player player = riskyPushHandlingTestSetUp();
		
		Assert.assertFalse(dealer.competeWith(player)); // dealer plays on 15 hand push
	}
	
	private Player cardTakingTestSetUp() {
		Card card1 = new Card("clubs", "5");
		Card card2 = new Card("diamonds", "4");
		dealer.take(card1);
		dealer.take(card2);
		
		Player player = new Player(100);
		Card card3 = new Card("clubs", "10");
		Card card4 = new Card("spades", "K");
		player.take(card3);
		player.take(card4);
		
		return player;
	}
	
	private Player pushHandlingTestSetUp() {
		Card card1 = new Card("clubs", "Q");
		Card card2 = new Card("diamonds", "5");
		dealer.take(card1);
		dealer.take(card2);
		
		Player player = new Player(100);
		Card card3 = new Card("clubs", "10");
		Card card4 = new Card("spades", "K");
		player.take(card3);
		player.take(card4);
		
		return player;
	}
	
	private Player riskyPushHandlingTestSetUp() {
		Card card1 = new Card("clubs", "Q");
		Card card2 = new Card("diamonds", "5");
		dealer.take(card1);
		dealer.take(card2);
		
		Player player = new Player(100);
		Card card3 = new Card("clubs", "10");
		Card card4 = new Card("spades", "5");
		player.take(card3);
		player.take(card4);
		
		return player;
	}
	
}
