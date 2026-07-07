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
		while (players.size()>=1) {
			String p1= players.poll();
			String p2= players.poll();
			String winner = playRound(p1,p2);
			if (winner!=null) {
				System.out.println("The winner is:" + winner + " !");
				players.add(winner);
			}else {
				System.out.println ("It is a tie!");
				players.add(p1);
				players.add(p2);
			}
			
		}
		if (players.isEmpty()) {
			return null ;
		}else {
		return players.poll();
	}}

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
		Move p1Move = randomMove();
		Move p2Move = randomMove();
		System.out.println (player1 + " uses "+ p1Move + " vs "+ player2 + " uses " + p2Move);
	if (p1Move== p2Move) {
		return null;
	} else if (beats (p1Move,p2Move)) {
		return player1;
	} else {
		return player2;
	}}

	/**
	 * Returns a random move: "ROCK", "PAPER", or "SCISSORS".
	 *
	 */
	public Move randomMove() {
		return Move.values()[random.nextInt(3)];
	
		
	}

	/**
	 * Returns true if move1 beats move2 according to standard Rock-Paper-Scissors
	 * rules.
	 *
	 * TODO: implement the three winning cases.
	 */
	public static boolean beats(Move move1, Move move2) {
		if (move1.equals(Move.ROCK)&&move2.equals(Move.SCISSORS)
				|| move1.equals(Move.SCISSORS)&&move2.equals(Move.PAPER) 
						||move1.equals(Move.PAPER)&&move2.equals(Move.ROCK )){
						return true;	
						}
		return false;
	}
}
