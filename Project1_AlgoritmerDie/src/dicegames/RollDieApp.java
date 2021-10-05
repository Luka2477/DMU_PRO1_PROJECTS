package dicegames;

/**
 * This application instantiates the PlayRollDie game and starts it.
 */
public class RollDieApp {

    /**
     * The main method. Don't call this one directly.
     * @param args the program arguments
     */
	public static void main(String[] args) {
		PlayRollDie play = new PlayRollDie();
		play.startGame();
	}

}
