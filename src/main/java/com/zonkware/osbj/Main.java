package com.zonkware.osbj;

import java.util.Scanner;

public class Main {
	
	private static Game game;
	private static Player player;
	private static Dealer dealer;
	
	public static void main(String[] args) {
		player = new Player(200);
		game = new Game(player, 500);
		dealer = game.getDealer();
		
		System.out.println("Player has $" + player.getMoney());
		newGame();
	}
	
	public static void newGame() {
		game.newGame();
		
		requestBet();
		game.startingDeal();
		
		System.out.println("Player has: " + player.totalHand());
		System.out.println("Player cards: " + player.getHand().get(0) + " // " + player.getHand().get(1));
		System.out.println("Dealer has: " + dealer.totalHand());
		System.out.println("Dealer cards: " + dealer.getHand().get(0) + " // " + dealer.getHand().get(1));
		
		if (dealer.hasBlackJack() || player.hasBlackJack()) {
			System.out.println("** BLACKJACK **");
			concludeMatch();
		} else {
			prompt();
			dealersTurn();
		}
	}
	
	public static void requestBet() {
		System.out.println("Place your bet: ");
		Scanner betPrompt = new Scanner(System.in);
		String bet = betPrompt.nextLine();
		
		if (Integer.parseInt(bet) > player.getMoney()) {
			System.out.println("You don't have that much!");
			requestBet();
		} else {
			int wager = Integer.parseInt(bet);
			int addToPot = wager * 2;
			game.takeBetFrom(player, wager);
			game.takeBetFrom(dealer, wager);
			
			System.out.println("Dealer matches your bet, " + addToPot + " in the pot!");
		}
	}
	
	public static void prompt() {
		System.out.println("(H)it or (S)tand: ");
		processAnswer();
	}
	
	public static void processAnswer() {
		Scanner scanIn = new Scanner(System.in);
		String answer = scanIn.nextLine();
		
		if (answer.toLowerCase().equals("h")) {
			Card card = game.dealCardTo(player);
			System.out.println("Dealt a " + card);
			
			if (player.isBusted()) {
				System.out.println("You busted! Your hand is now: " + player.totalHand());
				concludeMatch();
			} else {
				System.out.println("Your hand is now: " + player.totalHand());
				prompt();
			}
		} else {
			player.stand();
			System.out.println("Player stands with a hand of: " + player.totalHand());
		}
	}
	
	public static void dealersTurn() {
		if (player.isStanding()) {
			boolean isDealerStanding = dealer.competeWith(player);
			
			if (isDealerStanding) {
				concludeMatch();
			} else {
				Card card = game.dealCardTo(dealer);
				System.out.println("Dealer dealt a " + card);
				System.out.println("Dealer's total hand: " + dealer.totalHand());
				dealersTurn();
			}
		}
	}
	
	public static void concludeMatch() {
		if (dealer.beats(player)) {
			System.out.println("Dealer wins with a total hand of: " + dealer.totalHand());
			game.reward(dealer);
		} else if (player.beats(dealer)) {
			System.out.println("Player wins with a total hand of: " + player.totalHand());
			game.reward(player);
		} else {
			System.out.println("It's a push.");
			game.push();
		}
		
		System.out.println("Dealer's final hand: " + dealer.totalHand());
		System.out.println("Player's final hand: " + player.totalHand());
		System.out.println("Your wallet now has: " + player.getMoney());
		
		if (player.getMoney() == 0) {
			System.out.println("You're OUT of money!!! Thanks for playing.");
		} else {
			playAgain();
		}
	}
	
	public static void playAgain() {
		System.out.println("PLAY AGAIN? (Y) or (N):");
		Scanner playPrompt = new Scanner(System.in);
		
		if (playPrompt.nextLine().toLowerCase().equals("y")) {
			newGame();
		} else {
			System.out.println("Thanks for playing!!");
		}
	}
	
}
