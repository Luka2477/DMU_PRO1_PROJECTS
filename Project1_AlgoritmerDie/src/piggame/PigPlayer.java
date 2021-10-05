package piggame;

import dicegames.Die;

public class PigPlayer {
    private final Die die;
    private String name;
    private int score = 0;
    private int turnScore = 0;
    private int turns = 0;
    private int rolls = 0;

    public PigPlayer() {
        this.die = new Die();
    }

    public int roll() {
        this.die.roll();
        this.turnScore += this.die.getFaceValue();
        this.rolls++;

        return this.die.getFaceValue();
    }

    public void addScore() {
        this.score += this.turnScore;
        this.resetScore();
    }

    public void resetScore() {
        this.turnScore = 0;
    }

    public void addTurn() {
        this.turns++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public int getTurnScore() {
        return this.turnScore;
    }

    public int getTurns() {
        return this.turns;
    }

    public int getAverageRolls() {
        return this.rolls / this.turns;
    }
}
