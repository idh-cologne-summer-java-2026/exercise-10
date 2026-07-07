package idh.java;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/**
 * Hangman
 *
 * The secret word is passed in when a Hangman object is created. The
 * player guesses one letter at a time. The program should:
 *   - Keep track of every letter guessed so far in a Set (no duplicates
 *     possible, and checking "have we seen this letter?" is instant).
 *   - Display the word so far, showing guessed letters and blanks (_)
 *     for letters not yet guessed.
 *   - Count wrong guesses, and end the game after too many mistakes.
 *   - Warn the player if they guess a letter they already tried.
 *
 * Your job: implement the methods marked with TODO.
 */
public class Hangman {

    private static final int MAX_WRONG_GUESSES = 6;

    private final String secretWord;
    private final Set<Character> guessedLetters;
    private int wrongGuesses;

    public Hangman(String secretWord) {
        this.secretWord = secretWord.toUpperCase();
        this.guessedLetters = new HashSet<Character>();
        this.wrongGuesses = 0;
    }

    public static void main(String[] args) {
        Hangman game = new Hangman("computer");
        game.play();
    }

    /**
     * Runs the game loop until the player wins or runs out of guesses.
     * Already implemented for you -- no TODO here.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (wrongGuesses < MAX_WRONG_GUESSES) {
            System.out.println(getHangmanArt());
            System.out.println("Word: " + buildDisplayWord());
            System.out.println("Wrong guesses: " + wrongGuesses + "/" + MAX_WRONG_GUESSES);

            if (isWordFullyGuessed()) {
                System.out.println("You won! The word was " + secretWord);
                break;
            }

            System.out.print("Guess a letter: ");
            char guess = scanner.nextLine().toUpperCase().charAt(0);

            if (alreadyGuessed(guess)) {
                System.out.println("You already tried '" + guess + "'! Try a different letter.");
                continue;
            }

            guessedLetters.add(guess);

            if (secretWord.indexOf(guess) == -1) {
                wrongGuesses++;
                System.out.println("'" + guess + "' is not in the word.");
            } else {
                System.out.println("'" + guess + "' is in the word!");
            }
        }

        if (wrongGuesses == MAX_WRONG_GUESSES) {
            System.out.println(getHangmanArt());
            System.out.println("You lost! The word was " + secretWord);
        }

        scanner.close();
    }

    /**
     * Returns true if this letter has already been guessed before.
     *
     * TODO: implement using Set.contains() on guessedLetters
     */
    public boolean alreadyGuessed(char letter) {
        if (guessedLetters.contains(letter)) {
        	return true;
        }
        return false;
    }

    /**
     * Builds the current display version of the word, e.g. for secretWord
     * "COMPUTER" and guessedLetters {C, O, T}, this should return:
     *   "C O _ _ _ T E R"
     * Only show letters that are in guessedLetters; otherwise show '_'.
     * Separate each character with a space for readability.
     *
     * TODO:
     *  1. Build a String (or StringBuilder) by looping over each character
     *     of secretWord.
     *  2. If the character is in guessedLetters, add it to the result.
     *     Otherwise add '_'.
     *  3. Add a space between characters for readability.
     */
    public String buildDisplayWord() {
        String DisplayWord = "";
        char[] sWord = secretWord.toCharArray();
        for (int a = 0; a < sWord.length; a++) {
        	if (guessedLetters.contains(sWord[a])) {
        		DisplayWord = DisplayWord + sWord[a];
        	}
        	else {
        		DisplayWord = DisplayWord + "_";
        	}
        }
        return DisplayWord;
    }

    /**
     * Returns true if every letter in secretWord is contained in
     * guessedLetters (i.e. the player has fully revealed the word).
     *
     * TODO:
     *  1. Loop over each character in secretWord.
     *  2. If any character is NOT in guessedLetters, return false.
     *  3. If you get through the whole word, return true.
     */
    public boolean isWordFullyGuessed() {
        char[] sWord = secretWord.toCharArray();
        for (int a = 0; a < sWord.length; a++) {
        	if (guessedLetters.contains(sWord[a]) != true){
        		return false;
        	}
        }
        return true;
    }

    /**
     * Returns the ASCII art for the current number of wrong guesses
     * (0 = empty gallows, MAX_WRONG_GUESSES = fully hanged figure).
     * Already implemented for you -- no TODO here.
     */
    public String getHangmanArt() {
        String[] stages = {
            // 0 wrong guesses
            "  +---+\n"
          + "  |   |\n"
          + "      |\n"
          + "      |\n"
          + "      |\n"
          + "      |\n"
          + "=========",
            // 1
            "  +---+\n"
          + "  |   |\n"
          + "  O   |\n"
          + "      |\n"
          + "      |\n"
          + "      |\n"
          + "=========",
            // 2
            "  +---+\n"
          + "  |   |\n"
          + "  O   |\n"
          + "  |   |\n"
          + "      |\n"
          + "      |\n"
          + "=========",
            // 3
            "  +---+\n"
          + "  |   |\n"
          + "  O   |\n"
          + " /|   |\n"
          + "      |\n"
          + "      |\n"
          + "=========",
            // 4
            "  +---+\n"
          + "  |   |\n"
          + "  O   |\n"
          + " /|\\  |\n"
          + "      |\n"
          + "      |\n"
          + "=========",
            // 5
            "  +---+\n"
          + "  |   |\n"
          + "  O   |\n"
          + " /|\\  |\n"
          + " /    |\n"
          + "      |\n"
          + "=========",
            // 6 (fully hanged)
            "  +---+\n"
          + "  |   |\n"
          + "  O   |\n"
          + " /|\\  |\n"
          + " / \\  |\n"
          + "      |\n"
          + "========="
        };

        int index = Math.min(wrongGuesses, stages.length - 1);
        return stages[index];
    }
}