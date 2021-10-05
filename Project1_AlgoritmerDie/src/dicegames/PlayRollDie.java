package dicegames;

import java.util.Scanner;

/**
 * A small game where you roll dices. There are no rules; just roll the die until you get bored.
 */
public class PlayRollDie {
    /**
     * How many rolls have been rolled.
     */
	private int rolls;
	
	/**
	 * The scanner used for reading user input.
	 */
	private Scanner scan;
	
	/**
	 * The die used in the game.
	 */
	private Die die;

	/**
	 * Constructs the PlayRollDie game.
	 */
	public PlayRollDie() {
		die = new Die();
		scan = new Scanner(System.in);
	}

	/** 
	 * Print out a neat welcome message to the player.
	 */
	private void welcomeToGame() {
		System.out.println("Velkommen til spillet KAST terning");
	}

	/**
	 * Finishes the game and prints out the result.
	 */
	private void gameOver() {

		System.out.println("Tak for spillet du kastede " + rolls + " "
				+ "gange.");
		scan.close();
	}

	/**
	 * Take another turn in the game.
	 */
	private void takeTurn() {
		die.roll();
		int roll = die.getFaceValue();
		rolls++;
		System.out.println("Du har kastet: " + roll);
	}

	/**
	 * Start the game loop.<br/>
	 * The game is finished when the player chooses to not roll the die anymore.
	 */
	public void startGame() {
		welcomeToGame();

		boolean finished = false;

		while (!finished) {
			System.out.println("Vil du kaste en terning? Angiv Ja eller Nej: ");
			String goOn = scan.nextLine();
			if (goOn.equalsIgnoreCase("Nej")) {
				finished = true;
			} else {
				takeTurn();
			}
		}
		gameOver();
	}
}
