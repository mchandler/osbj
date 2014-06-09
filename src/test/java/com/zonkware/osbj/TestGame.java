package com.zonkware.osbj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGame {
	
	private Game game;
	
	@Before
	public void setUp() {
		game = new Game(500);
		game.getPlayer().setMoney(200);
	}
	
	@Test
	public void testStartingBank() {
		Assert.assertEquals(500, game.getDealer().getMoney());
	}
	
	@Test
	public void testNewDeck() {
		Assert.assertEquals(52, game.getDeck().size());
	}
	
	@Test
	public void testStartingDeal() {
		game.startingDeal();
		Assert.assertEquals(48, game.getDeck().size());
		Assert.assertEquals(2, game.getPlayer().getHand().size());
		Assert.assertEquals(2, game.getDealer().getHand().size());
	}
	
	@Test
	public void testDealCardToPlayer() {
		Card card = game.dealCardTo(game.getPlayer());
		Assert.assertTrue(game.getPlayer().getHand().contains(card));
		Assert.assertFalse(game.getDeck().contains(card));
	}
	
	@Test
	public void testShuffleLogic() {
		for (int i = 1; i <= 50; i++) {
			game.dealCardTo(game.getPlayer());
		}
		
		Assert.assertEquals(2, game.getDeck().size());
		
		game.newGame(); // clears all hands and makes all cards available for reshuffle
		
		for (int x = 1; x <= 3; x++) {
			game.dealCardTo(game.getPlayer());
		}
		
		Assert.assertEquals(49, game.getDeck().size());
	}
	
	@Test
	public void testBets() {
		Assert.assertEquals(200, game.getPlayer().getMoney());
		
		game.takeBetFrom(game.getPlayer(), 50);
		
		Assert.assertEquals(150, game.getPlayer().getMoney());
		
		game.takeBetFrom(game.getPlayer(), "100");
		
		Assert.assertEquals(50, game.getPlayer().getMoney());
	}
	
	@Test
	public void testRewards() {
		game.takeBetFrom(game.getDealer(), 100);
		game.reward(game.getPlayer());
		Assert.assertEquals(300, game.getPlayer().getMoney());
	}
	
	@Test
	public void testPushPayouts() {
		game.takeBetFrom(game.getDealer(), 100);
		game.push();
		
		Assert.assertEquals(250, game.getPlayer().getMoney());
		Assert.assertEquals(450, game.getDealer().getMoney());
	}
	
}
