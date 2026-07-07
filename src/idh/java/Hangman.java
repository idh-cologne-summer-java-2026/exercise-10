package idh.java;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/**
 * Hangman
 *
 * The secret word is passed in when a Hangman object is created. The
 * player guesses one letter at a time. The program should:
 * - Keep track of every letter guessed so far in a Set (no duplicates
 * possible, and checking "have we seen this letter?" is instant).
 * - Display the word so far, showing guessed letters and blanks (_)
 * for letters not yet guessed.
 * - Count wrong guesses, and end the game after too many mistakes.
 * - Warn the player if they guess a letter they already tried.
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
        // Falls du Kommandozeilenargumente nutzen willst:
        String word = args.length > 0 ? args[0] : "COMPUTER";
        Hangman game = new Hangman(word);
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
            String input = scanner.nextLine().trim().toUpperCase();
            
            if(input.isEmpty()) continue; // Fehler abfangen, falls einfach nur Enter gedrückt wird
            char guess = input.charAt(0);

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
     */
    public boolean alreadyGuessed(char letter) {
        // Ein Set macht diese Prüfung extrem einfach:
        return guessedLetters.contains(letter);
    }

    /**
     * Builds the current display version of the word, e.g. for secretWord
     * "COMPUTER" and guessedLetters {C, O, T}, this should return:
     * "C O _ _ _ T E R"
     * Only show letters that are in guessedLetters; otherwise show '_'.
     * Separate each character with a space for readability.
     */
    public String buildDisplayWord() {
        StringBuilder display = new StringBuilder();
        
        // Wir gehen jeden Buchstaben im geheimen Wort durch
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }
        
        // trim() entfernt das letzte überflüssige Leerzeichen am Ende
        return display.toString().trim();
    }

    /**
     * Returns true if every letter in secretWord is contained in
     * guessedLetters (i.e. the player has fully revealed the word).
     */
    public boolean isWordFullyGuessed() {
        // Wir prüfen, ob es IRGENDEINEN Buchstaben gibt, der noch nicht geraten wurde
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false; // Sobald ein Buchstabe fehlt -> Wort noch nicht komplett
            }
        }
        return true; // Alle Buchstaben waren im Set -> Wort ist komplett!
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

/*
 * ANTWORT AUF DIE THEORIE-FRAGE AUS DER AUFGABENSTELLUNG:
 * Warum bietet sich für die geratenen Buchstaben ein Set und keine List an?
 
 * 1. Keine Duplikate erlaubt: 
 * Ein Set (wie das hier verwendete HashSet) verhindert automatisch, dass 
 * Elemente doppelt gespeichert werden. Bei einer List müssten wir manuell 
 * prüfen und verhindern, dass derselbe Buchstabe mehrfach eingetragen wird.
 
 * 2. Bessere Performance (Geschwindigkeit): 
 * Die Methode .contains() ist bei einem HashSet extrem schnell (O(1)). Java 
 * weiß durch die Hash-Funktion sofort, ob der Buchstabe schon vorhanden ist. 
 * Bei einer List (O(n)) müsste Java im schlimmsten Fall die gesamte Liste 
 * von vorne bis hinten durchsuchen, um den Buchstaben zu finden.
 */