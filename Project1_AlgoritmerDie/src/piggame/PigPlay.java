package piggame;

import java.util.Scanner;

public class PigPlay {
    private final PigPlayer player1;
    private final PigPlayer player2;
    private PigPlayer currPlayer;
    private final Scanner scanner;

    private int endScore;
    private boolean gameState = true;

    public PigPlay() {
        this.player1 = new PigPlayer();
        this.player2 = new PigPlayer();
        this.scanner = new Scanner(System.in);
    }

    private void takeTurnComputer() {
        if(this.currPlayer.getTurnScore() == 0)
            if(!this.roll())
                return;

        if(Math.random() <= 0.6)
            this.roll();
        else
            this.endTurn(false);
    }

    private void takeTurnPlayer() {
        System.out.println();
        System.out.print("Vil du gerne kaste terningen? (Ja eller nej) ");

        if(!this.scanner.nextLine().equalsIgnoreCase("nej"))
            this.roll();
        else
            this.endTurn(false);
    }

    private boolean roll() {
        int roll = this.currPlayer.roll();

        System.out.println("Du slog " + roll + " og har slået " + this.currPlayer.getTurnScore() + " tilsammen denne runde!");

        if(roll == 1) {
            this.endTurn(true);
            return false;
        }

        return true;
    }

    private void endTurn(boolean hitOne) {
        System.out.println();

        if(hitOne) {
            this.currPlayer.resetScore();
            System.out.println("Du slog desværre et ét tal. Det betyder du ingen point får denne runde...");
        } else {
            this.currPlayer.addScore();
            System.out.println("Du valgte at stoppe. Du har nu " + this.currPlayer.getScore() + " point!");
        }

        this.changePlayer();
    }

    private void changePlayer() {
        if(this.currPlayer != null && this.currPlayer.getScore() >= this.endScore) {
            this.gameState = false;
            return;
        }

        if(this.currPlayer == null)
            this.currPlayer = this.player1;
        else if(this.currPlayer == this.player1)
            this.currPlayer = this.player2;
        else
            this.currPlayer = this.player1;

        System.out.println();
        System.out.println("Det er nu " + this.currPlayer.getName() + "'s tur!");
        this.currPlayer.addTurn();
    }

    public void startGame() {
        this.welcome();

        this.changePlayer();

        while(gameState) {
            if(this.currPlayer.getName().equals("Computer"))
                this.takeTurnComputer();
            else
                this.takeTurnPlayer();
        }

        this.gameOver();
    }

    private void welcome() {
        System.out.println("Velkommen til Pig spillet! Sådan foregår det...");
        System.out.println("Hver spiller skiftes til at kaste med en terning indtil han enten kaster 1, eller beslutter sig for at stoppe.");
        System.out.println("Hvis han slår en 1’er, får han ingen point i denne omgang.");
        System.out.println("Hvis han beslutter sig for at stoppe inden har slår en 1’er, lægges summen af alle hans kast i denne tur sammen");
        System.out.println("med hans samlede antal point, og turen går videre til næste spiller. Den første spiller der samlet når 100 point har vundet.");

        System.out.println();
        System.out.print("Venligst indtaste dit navn: ");
        this.player1.setName(this.scanner.nextLine());

        System.out.println();
        System.out.print("Vil du gerne spille med en ven? Alternativet er at du spiller mod computeren. (Ja eller Nej) ");
        boolean answer = this.scanner.nextLine().equalsIgnoreCase("nej");

        if(answer)
            this.player2.setName("Computer");
        else {
            System.out.println();
            System.out.print("Venligst indtaste spiller to's navn: ");
            this.player2.setName(this.scanner.nextLine());
        }

        System.out.println();
        System.out.print("Indtaste hvor meget du vil spille til: (Default 100) ");
        this.endScore = Integer.parseInt(this.scanner.nextLine());
    }

    private void gameOver() {
        System.out.printf("%s vandt!!%n", this.currPlayer.getName());
        System.out.printf("%s slog %d i gennemsnit på %d kast!%n", this.player1.getName(), this.player1.getAverageRolls(), this.player1.getTurns());
        System.out.printf("%s slog %d i gennemsnit på %d kast!%n", this.player2.getName(), this.player2.getAverageRolls(), this.player2.getTurns());
    }
}
