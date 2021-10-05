package crapsgame;

import dicegames.PairOfDices;

import java.util.Scanner;

public class CrapsPlay {
    private final PairOfDices dice;
    private final Scanner scanner = new Scanner(System.in);
    private int wins = 0;
    private int losses = 0;

    public CrapsPlay() {
        this.dice = new PairOfDices();
    }

    private int rollDice() {
        this.dice.rollBothDices();
        int roll = this.dice.sumOfDices();
        System.out.printf("Du slog %d! (Tryk \"enter\")", roll);
        this.scanner.nextLine();

        return roll;
    }

    private void welcomeToGame() {
        System.out.println("Velkommen til Craps!");
        System.out.println("Du vinder med det samme, hvis det første kast er 7 eller 11 og taber med det samme, hvis han opnår 2, 3 eller 12.");
        System.out.println("Hvis dit første kast er 4, 5, 6, 8, 9 eller 10, etableres dette tal som hans ‘point’.");
        System.out.println("Du bliver derefter ved med at kaste, indtil han opnår sit ‘point’ igen. Opnår han 7 før han opnår sit ‘point’, har han tabt.");
        System.out.print("Er du klar til at starte? (Tryk \"enter\")");
        this.scanner.nextLine();
    }

    private boolean gameOver(boolean gameState) {
        if(gameState) {
            this.wins++;
            System.out.println("Du har vundet!!!\n");
        } else {
            this.losses++;
            System.out.println("Du tabte HAHA!!!\n");
        }

        System.out.print("Vil du gerne spille igen? (Ja eller Nej) ");
        String answer = this.scanner.nextLine();

        if(answer.equalsIgnoreCase("nej")) {
            System.out.printf("Tak fordi du spillede! Du vandt %d gange og tabte %d gange! Kom tilbage en anden gang!", this.wins, this.losses);
            return false;
        }

        System.out.println("Fantastisk! Så spiller vi videre...\n");
        return true;
    }

    private boolean takeTurn() {
        int roll = this.rollDice();

        if(roll == 7 || roll == 11)
            return true;

        if(roll == 0 || roll == 2 || roll == 3 || roll == 12)
            return false;

        System.out.printf("Det betyder du skal slå det samme før du slår 7!%n");
        return this.takeTurn(roll);
    }

    private boolean takeTurn(int roll) {
        int newRoll = this.rollDice();

        if(newRoll == 7)
            return false;

        if(newRoll == roll)
            return true;

        return this.takeTurn(roll);
    }

    public void startGame() {
        this.welcomeToGame();

        while(true) {
            boolean gameState = this.takeTurn();

            if(!this.gameOver(gameState))
                break;
        }
    }
}
