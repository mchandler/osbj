package com.zonkware.osbj;

public class Dealer extends Player {
	
	private boolean willTakeCard;
	
	@Override
	public void take(Card card) {
		super.take(card);
		
		if (willTakeCard == true) {
			willTakeCard = false;
		}
	}
	
	public boolean competeWith(Player player) {
		if (player.beats(this)) {
			willTakeCard = true;
		}
		
		if (player.totalHand() == totalHand()) {
			processPushDecisions();
		}
		
		if (this.beats(player) || this.isBusted()) {
			stand();
		}
		
		return isStanding();
	}
	
	public void finishGameWith(Player player) {
		while (!isStanding()) {
			competeWith(player);
		}
	}
	
	private void processPushDecisions() {
		if (totalHand() >= 16) {
			stand();
		} else {
			willTakeCard = true;
		}
	}
}
