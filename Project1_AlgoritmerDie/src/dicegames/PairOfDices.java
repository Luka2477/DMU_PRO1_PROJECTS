package dicegames;

import java.util.Arrays;

/**
 * This class models one pair of dices.
 *
 */
public class PairOfDices {
    /**
     * The first die in the pair.
     */
	private final Die die1;
	/**
	 * The second die in the pair.
	 */
	private final Die die2;

	private int rolls = 0;
	private final int[] numbers;
	private int pairs = 0;
	private int maxRoll = 0;

	/**
	 * Constructor for objects of class PairOfDices
	 */
	public PairOfDices() {
		this.die1 = new Die();
		this.die2 = new Die();
		this.numbers = new int[6];
	}

	public PairOfDices(int sides) {
		this.die1 = new Die(sides);
		this.die2 = new Die(sides);
		this.numbers = new int[sides];
	}

	public void rollBothDices() {
		this.die1.roll();
		this.die2.roll();

		this.rolls++;

		this.numbers[this.die1.getFaceValue() - 1]++;
		this.numbers[this.die2.getFaceValue() - 1]++;

		if(this.die1.getFaceValue() == this.die2.getFaceValue())
			this.pairs++;

		if(this.sumOfDices() > this.maxRoll)
			this.maxRoll = this.sumOfDices();
	}

	public int sumOfDices() {
		return this.die1.getFaceValue() + this.die2.getFaceValue();
	}

	public void resetPairOfDice() {
		this.die1.setFaceValue(1);
		this.die2.setFaceValue(1);
		this.rolls = 0;
		this.pairs = 0;
		this.maxRoll = 0;

		Arrays.fill(this.numbers, 0);
	}

	public int getRolls() {
		return this.rolls;
	}

	public int getNumber(int number) {
		return this.numbers[number - 1];
	}

	public int getPairs() {
		return this.pairs;
	}

	public int getMaxRoll() {
		return this.maxRoll;
	}
}
