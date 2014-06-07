package com.zonkware.osbj;

import org.junit.Assert;
import org.junit.Test;

public class TestCard {
	
	@Test
	public void testValue() {
		Card jack = new Card("spades", "J");
		Card ace = new Card("spades", "A");
		Card card = new Card("spades", "5");
		
		Assert.assertEquals(10, jack.getValue());
		Assert.assertEquals(11, ace.getValue());
		Assert.assertEquals(5, card.getValue());
	}
	
	@Test
	public void testEqualsOverride() {
		Card card1 = new Card("spades", "ace");
		Card card2 = new Card("spades", "ace");
		
		Assert.assertFalse(card1.hashCode() == card2.hashCode());
		Assert.assertTrue(card1.equals(card2));
	}
	
}
