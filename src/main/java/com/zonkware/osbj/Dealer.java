package com.zonkware.osbj;

public class Dealer extends Player {
	
	@Override
	public void take(Card card) {
		super.take(card);
		
		setStanding(false);
	}
	
	public boolean competeWith(Player player) {
		if (player.beats(this)) {
			setStanding(false);
		}
		
		if (player.totalHand() == totalHand()) {
			processPushDecisions();
		}
		
		if (this.beats(player) || this.isBusted()) {
			stand();
		}
		
		return isStanding();
	}
	
	private void processPushDecisions() {
		if (totalHand() >= 16) {
			stand();
		} else {
			setStanding(false);
		}
	}
}
