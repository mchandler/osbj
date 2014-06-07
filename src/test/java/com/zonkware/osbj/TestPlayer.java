package com.zonkware.osbj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPlayer {
	
	private Player player;
	private Player player2;
	
	@Before
	public void setUp() {
		player = new Player(100);
		Card card = new Card("hearts", "Q");
		player.take(card);
		
		player2 = new Player(100);
		Card card2 = new Card("diamonds", "Q");
		player2.take(card2);
	}
	
	@Test
	public void testTotalHand() {
		Card card2 = new Card("spades", "5");
		player.take(card2);
		
		Assert.assertEquals(15, player.totalHand());
	}
	
	@Test
	public void testBlackJack() {
		Card card2 = new Card("clubs", "A");
		player.take(card2);
		
		Assert.assertTrue(player.hasBlackJack());
	}
	
	@Test
	public void testAceLogic() {
		Card card1 = new Card("hearts", "9");
		Card card2 = new Card("spades", "A");
		player.take(card2);
		player.take(card1);
		
		Assert.assertEquals(20, player.totalHand());
	}
	
	@Test
	public void testBusted() {
		Card card1 = new Card("clubs", "Q");
		Card card2 = new Card("spades", "10");
		player.take(card1);
		player.take(card2);
		
		Assert.assertTrue(player.isBusted());
	}
	
	@Test
	public void testBeats() {
		Card card1 = new Card("clubs", "K");
		player.take(card1);
		
		Card card2 = new Card("spades", "2");
		player2.take(card2);
		
		Assert.assertTrue(player.beats(player2));
		Assert.assertFalse(player2.beats(player));
	}
	
	@Test
	public void testReward() {
		player.reward(50);
		Assert.assertTrue(player.getMoney() == 150);
	}
	
	@Test
	public void testBet() {
		player.bet(50);
		Assert.assertTrue(player.getMoney() == 50);
	}
}
