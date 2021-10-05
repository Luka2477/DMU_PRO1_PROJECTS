package dicegames;

import java.util.Scanner;

public class PlayPairOfDice {
    private final PairOfDices dice;
    private final int sides;

    public PlayPairOfDice() {
        this.dice = new PairOfDices();
        this.sides = 6;
    }

    public PlayPairOfDice(int sides) {
        this.dice = new PairOfDices(sides);
        this.sides = sides;
    }

    private boolean takeTurn(String answer) {
        if(answer.equalsIgnoreCase("nej")) return false;

        this.dice.rollBothDices();
        System.out.printf("You rolled %d!%n", this.dice.sumOfDices());

        return true;
    }

    private void gameOver() {
        System.out.printf("Du slog ens %d gange og dit største slag var %d!!!%n", this.dice.getPairs(), this.dice.getMaxRoll());

        for(int i=0; i<this.sides; i++)
            System.out.printf("Du slog %d, %d gange!!%n", i+1, this.dice.getNumber(i+1));
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Vil du gerne kaste terningerne? (Ja eller Nej) ");
            String answer = scanner.nextLine();

            if(!this.takeTurn(answer))
                break;
        }

        this.gameOver();
        scanner.close();
    }
}
