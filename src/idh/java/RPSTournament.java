package idh.java;

import java.util.LinkedList;
import java.util.NoSuchElementException;
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
	Queue<String> players = new LinkedList<String>(); //List containing the players. Used in all subsequent methods.

	public static void main(String[] args) {

		RPSTournament rps = new RPSTournament();
		rps.addPlayer("Alice"); //Player String Objects added to Queue, using add Players() below.
		rps.addPlayer("Bob");
		rps.addPlayer("Charlie");
		rps.addPlayer("Dana");
		rps.addPlayer("Eve");
		String champion = rps.runTournamentActual(); //playing the game until a winner is decided
		System.out.println("\nThe champion is: " + champion);
	}
	/** Used to add Player String Objects into the Queue**/
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
	 * player remains, return their name. CORRECTION NEEDED
	 */
//	public String runTournament() {
//		String firstPlayer = null, secondPlayer = null;
//		Queue <String> tempQueue = new LinkedList<String>();
//		while(players.peek() != null) {
//		for(int i = 0; players.size() > 1 && i < 2; i++) {
//			String removed = players.remove(); //removes the first 2 Players
//			tempQueue.add(removed); //save in new Queue to work with
//			firstPlayer = tempQueue.poll();
//			secondPlayer = tempQueue.poll();
//			}
//		}
//		return this.playRound(firstPlayer, secondPlayer); //enters first 2 players in next round
//	}

	public String runTournamentActual() {
		Queue <String> currentRoster = new LinkedList <String>(players); //gets players Queue as parameter
//		Queue <String> nextRoster = new LinkedList<String>(); //this queue would get the players passing the 1st round, cannot pass same Queue players here
		
		if(currentRoster.isEmpty()) {
			throw new NoSuchElementException("No element found, queue is empty.");
		}
		
		//tournament continues as long as more than 1 player is left
		while(currentRoster.size() > 1) {
			Queue<String>nextRoster = new LinkedList<String>();
			
			
			while(currentRoster.size() >= 2) {
			String p1 = currentRoster.remove(); //take out a player and save it to variable
			String p2 = currentRoster.remove();
			String roundWinner = playRound(p1,p2); //plays a round, returns winner + save to variable
			
			//In case of a draw: No winner (== null), hence we try again
			while(roundWinner == null) {
				System.out.println("No winner, playing another round.");
				roundWinner = playRound(p1, p2);
			}
			}
			nextRoster.add(roundWinner); //put winner in Queue for the next Round
		}
//		currentRoster = nextRoster;
		
		if(currentRoster.size() == 1) {
			String loneP = currentRoster.remove();
			System.out.println(loneP + " has no opponent left, automaticlly wins.");
			nextRoster.add(loneP);
		}
		currentRoster = nextRoster;
		return currentRoster.poll();//retrieves + returns last remaining player --> Winner
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
		RPSTournament player = new RPSTournament();
		Move p1M = player.randomMove(); Move p2M = player.randomMove(); //Random moves for each player
		System.out.println(player1 + ": " + p1M + " | " + player2 + ": " + p2M + "." );
		
		if(beats(p1M, p2M)) {
			System.out.println(player1 + "(" + p1M + ")" + " " + "Winner");
			return player1;
		}
		
		if(beats(p2M, p1M)) {
			System.out.println(player2 + "(" + p2M + ")" + " Winner");
//			System.out.println(player2 + "(" + p2M + ")" + " " + "Winner: " + player2);
			return player2;
		}
		System.out.println("Draw.");
		return null;
	}

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
	 * 
	 */
	public static boolean beats(Move move1, Move move2) {
		switch(move1) {
		case ROCK:
			return move2 == Move.SCISSORS;
		case PAPER:
			return move2 == Move.ROCK;
		case SCISSORS:
			return move2 == Move.PAPER;
		default: System.out.println("Something went wrong.");
			throw new IllegalArgumentException("Something went wrong");
		}
	}
}
