package idh.java;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Rock-Paper-Scissors Tournament Bracket
 *
 * Rules: - All players start in a queue. - Repeatedly take the first two
 * players from the front of the queue and let them play one round of
 * Rock-Paper-Scissors. - The winner goes to the BACK of the queue. The loser is
 * eliminated. - On a tie, replay the round (nobody is eliminated). - If only
 * one player is left in the queue, that player is the champion.
 *
 * Your job: implement the methods marked with TODO.
 */
public class RPSTournament {

	Random random = new Random();

	/**
	 * We haven't used this so far, it's a quick and easy way to define a number of
	 * categories without using strings
	 * (https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html).
	 * <code>Move</code> is now a regular type, and can be used anywhere we use
	 * other kinds of types. <code>Move.ROCK</code> can be used to express the
	 * literal value of one category.
	 */
	enum Move {
		ROCK, PAPER, SCISSORS
	};

	Queue<String> players = new LinkedList<String>();

	public static void main(String[] args) {

		RPSTournament rps = new RPSTournament();
		rps.addPlayer("Alice");
		rps.addPlayer("Bob");
		rps.addPlayer("Charlie");
		rps.addPlayer("Dana");
		rps.addPlayer("Eve");

		String champion = rps.runTournament();
		System.out.println("\n The champion is: " + champion);
	}

	public void addPlayer(String playerName) {
		this.players.add(playerName);
	}

	/**
	 * Runs the tournament until only one player remains. Returns the name of the
	 * champion.
	 *
	 * TODO: 1. While there is more than one player in the queue: a. Dequeue the
	 * first two players. b. Play a round between them using playRound(). c. If
	 * there's a winner, print the result and enqueue the winner at the back of the
	 * queue. d. If it's a tie, print that it's a tie and put BOTH players back in
	 * the queue (order doesn't matter much, but be consistent). 2. When only one
	 * player remains, return their name.
	 */
	public String runTournament() {
		while(players.size() > 1) {
			String player1 = players.remove();
			String player2 = players.remove();
			
			String winner = playRound(player1, player2);
			if (winner != null) {
				System.out.println("winner is: " + winner);
				players.add(winner);
			} else {
				System.out.println("it's a tie!");
				players.add(player1);
				players.add(player2);
			}
		}
		return players.remove();
	}

	/**
	 * Plays one round of Rock-Paper-Scissors between two players. Returns the name
	 * of the winner, or null if it's a tie.
	 *
	 * TODO: 1. Generate a random move for each player using randomMove(). 2. Print
	 * something like "Alice (ROCK) vs Bob (SCISSORS)". 3. Use beats() to determine
	 * the winner and return their name. 4. If both moves are the same, return null
	 * (tie).
	 */
	public String playRound(String player1, String player2) {
		Move m1 = randomMove();
		Move m2 = randomMove();
		System.out.println(player1 + " (" +  m1 + ") vs. " + player2 + " (" + m2 + ")");
		
		if(m1 == m2) {
			return null;
		} else if (beats(m1, m2)) {
			return player1;
		} else {
			return player2;
		}
		
	}

	/**
	 * Returns a random move: "ROCK", "PAPER", or "SCISSORS".
	 *
	 */
	public Move randomMove() {
		return Move.values()[random.nextInt(3)];
	}

	
	public static boolean beats(Move move1, Move move2) {
		return (move1 == Move.ROCK && move2 == Move.SCISSORS)
				|| (move1 == Move.PAPER && move2 == Move.ROCK)
				|| (move1 == Move.SCISSORS && move2 == Move.PAPER);
	}
}
