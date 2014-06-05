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
	
	public boolean concludeByStep(Player player) {
		if (player.beats(this)) {
			willTakeCard = true;
		}
		
		if (player.totalHand() == totalHand()) {
			return processPushDecisions();
		}
		
		if (this.beats(player)) {
			stand();
		}
		
		return isStanding();
	}
	
	private boolean processPushDecisions() {
		if (totalHand() >= 16) {
			stand();
		} else {
			willTakeCard = true;
		}
		
		return isStanding();
	}
}
