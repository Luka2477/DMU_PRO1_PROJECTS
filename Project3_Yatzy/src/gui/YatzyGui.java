package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Yatzy;

import java.util.Arrays;

public class YatzyGui extends Application {
	@Override
	public void start(Stage stage) {
		stage.setTitle("Yatzy");
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	// -------------------------------------------------------------------------

	// Shows the face values of the 5 dice.
	private TextField[] txfValues;
	// Shows the hold status of the 5 dice.
	private CheckBox[] chbHolds;
	// Shows the results previously selected .
	// For free results (results not set yet), the results
	// corresponding to the actual face values of the 5 dice are shown.
	private TextField[] txfResults;
	// Show points in sums, bonus and total.
	private TextField[] txfSumBonusSumTotal;
	// Shows the number of times the dice has been rolled.
	private Label lblRolled;

	private Button btnRoll;

	private final int[] lockedResults = new int[15];

	private void initContent(GridPane pane) {
		pane.setGridLinesVisible(false);
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);

		// ---------------------------------------------------------------------

		GridPane dicePane = new GridPane();
		pane.add(dicePane, 0, 0);
		dicePane.setGridLinesVisible(false);
		dicePane.setPadding(new Insets(10));
		dicePane.setHgap(10);
		dicePane.setVgap(10);
		dicePane.setStyle("-fx-border-color: black");

		// Initialize text fields that show dice face values and hold checkboxes
		this.txfValues = new TextField[5];
		this.chbHolds = new CheckBox[5];

		// Generate dice face value text fields and hold checkboxes
		for (int i=0; i<this.txfValues.length; i++) {
			TextField txfValue = new TextField();
			txfValue.setEditable(false);
			txfValue.setPrefSize(75, 75);
			txfValue.setFont(new Font(35));
			txfValue.setAlignment(Pos.CENTER);

			CheckBox chbHold = new CheckBox("Hold");
			chbHold.setFont(new Font(10));
			// Center the checkbox horizontally within the column
			GridPane.setHalignment(chbHold, HPos.CENTER);

			// Add text fields and checkboxes to grid pane and their arrays respectively
			dicePane.add(txfValue, i, 0);
			this.txfValues[i] = txfValue;

			dicePane.add(chbHold, i, 1);
			this.chbHolds[i] = chbHold;
		}

		// Add button to roll the dice
		Button btnRoll = new Button("Roll");
		btnRoll.setFont(new Font(20));
		btnRoll.setOnAction(event -> this.handleBtnRoll());
		GridPane.setHalignment(btnRoll, HPos.CENTER);

		// Add label for how many times the dice have been rolled
		Label lblRolled = new Label("Rolled: 0");
		lblRolled.setFont(new Font(10));
		GridPane.setHalignment(lblRolled, HPos.CENTER);

		dicePane.add(btnRoll, 3, 2);
		this.btnRoll = btnRoll;

		dicePane.add(lblRolled, 4, 2);
		this.lblRolled = lblRolled;

		// ---------------------------------------------------------------------

		GridPane scorePane = new GridPane();
		pane.add(scorePane, 0, 1);
		scorePane.setGridLinesVisible(false);
		scorePane.setPadding(new Insets(10));
		scorePane.setVgap(5);
		scorePane.setHgap(10);
		scorePane.setStyle("-fx-border-color: black");
		int w = 50; // width of the text fields

		// Hold all the label texts for the results in an array so that we can iterate over them
		String[] lblResults = {"1-s", "2-s", "3-s", "4-s", "5-s", "6-s", "One pair", "Two pairs",
				"Three same", "Four same", "Full House", "Small Straight", "Large Straight", "Chance", "Yatzy"};
		// Initialize text fields for the results
		this.txfResults = new TextField[15];

		// Generate label and text field for each result
		for (int i=0; i<this.txfResults.length; i++) {
			Label lblResult = new Label(lblResults[i]);
			lblResult.setFont(new Font(10));

			TextField txfResult = new TextField();
			txfResult.setEditable(false);
			txfResult.setDisable(true);
			// Using preferred height to make the text fields not change size when focused
			txfResult.setPrefSize(w, 22);
			txfResult.setFont(new Font(10));
			// Make text hug the right wall of the text field
			txfResult.setAlignment(Pos.CENTER_RIGHT);
			// Add action on mouse click. "this::handleLockResult" automatically sends the event to the method
			txfResult.setOnMouseClicked(this::handleLockResult);

			scorePane.add(lblResult, 0, i);
			scorePane.add(txfResult, 1, i);
			this.txfResults[i] = txfResult;
		}

		// Store the label texts for the sums, bonus and total, so they are iterable
		String[][] lbls = {{"Sum:", "Bonus:"}, {"Sum:", "Total:"}};
		// Store the row index for the sums, bonus and total so they are iterable
		int[] rows = {5, 14};
		// Initialize text fields for sums, bonus and total. They will be stored as such
		// Singles-sum index = 0, bonus index = 1, other-sums index = 2, total index = 3
		this.txfSumBonusSumTotal = new TextField[4];

		// Generate labels and text fields for the sums, bonus and total
		for (int row=0; row<rows.length; row++) {
			for (int col=0; col<lbls.length; col++) {
				Label lbl = new Label(lbls[row][col]);
				lbl.setFont(new Font(10));

				TextField txf = new TextField("0");
				txf.setEditable(false);
				txf.setPrefSize(w, 22);
				txf.setFont(new Font(10));
				txf.setAlignment(Pos.CENTER_RIGHT);
				// Make the text in the text field bold, blue and of static size
				txf.setStyle("-fx-font-weight: bold; -fx-text-fill: blue; -fx-font-size: 7pt;");

				// Make sure the label and text field is positioned in the right column and row
				scorePane.add(lbl, col * 2 + 2, rows[row]);
				scorePane.add(txf, col * 2 + 3, rows[row]);
				this.txfSumBonusSumTotal[row * 2 + col] = txf;
			}
		}

		// Fill the lockedResults array with -1s to tell the difference between what is used and isn't
		Arrays.fill(this.lockedResults, -1);
	}

	// -------------------------------------------------------------------------

	private final Yatzy dice = new Yatzy();

	/**
	 * Handles what happens when the Roll button is clicked
	 */
	private void handleBtnRoll () {
		this.throwDice();

		this.updateValues();

		// If the dice have been thrown 3 times then the hold checkboxes and Roll button should be disabled
		if (this.dice.getThrowCount() == 3)	this.rollAndHoldsDisabled(true);

		this.updateResults();
	}

	/**
	 * Throws the dice
	 */
	private void throwDice() {
		// Initialize boolean array that stores whether the hold checkboxes are selected
		boolean[] holds = new boolean[5];
		for (int i=0; i<holds.length; i++) holds[i] = this.chbHolds[i].isSelected();

		this.dice.throwDice(holds);
	}

	/**
	 * Updates the text fields that show the dice face values
	 * Also updates the roll counter
	 */
	private void updateValues () {
		int[] faceValues = this.dice.getValues();
		// Set the text fields showing the dice face values to the new face values
		for (int i=0; i<faceValues.length; i++) this.txfValues[i].setText(Integer.toString(faceValues[i]));
		this.lblRolled.setText("Rolled: " + this.dice.getThrowCount());
	}

	/**
	 * Changes the disabled status on the hold checkboxes and Roll button
	 *
	 * @param shouldBeDisabled if chbHolds and btnRoll should be disabled
	 */
	private void rollAndHoldsDisabled (boolean shouldBeDisabled) {
		this.btnRoll.setDisable(shouldBeDisabled);
		for (CheckBox chbHold : this.chbHolds) chbHold.setDisable(shouldBeDisabled);
	}

	/**
	 * Registers the new results in the respective text fields
	 */
	private void updateResults () {
		int[] results = this.dice.getResults();
		for (int i=0; i<results.length; i++) {
			// If the result isn't locked, then it should be updated and enabled
			if (this.lockedResults[i] == -1) {
				this.txfResults[i].setText(Integer.toString(results[i]));
				this.txfResults[i].setDisable(false);
			} else this.txfResults[i].setDisable(true);
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * Handles what happens when a result text field is clicked
	 *
	 * @param event the Event that stores what text field was clicked
	 */
	private void handleLockResult (Event event) {
		TextField txfResult = (TextField) event.getSource();
		int index = this.findTxfIndex(txfResult);

		// If the result is locked, then we don't want to do anything with it
		if (this.lockedResults[index] != -1) return;

		this.lockResult(txfResult, index);

		this.updateSums();

		this.resetRolls();

		// If all results are locked AKA if -1 doesn't exist in the lockedResults array,
		// then call the gameOver method
		if (Arrays.stream(this.lockedResults).noneMatch(result -> result == -1)) this.gameOver();
	}

	/**
	 * Returns the index of the text field in the txfResults array
	 * This is used to insert its value in the lockedResults array
	 *
	 * @param txf the text field to look for
	 * @return the index of the text field in the txfResults array
	 */
	private int findTxfIndex (TextField txf) {
		for (int i=0; i<this.txfResults.length; i++)
			if (this.txfResults[i] == txf) return i;

		return -1;
	}

	/**
	 * Register the result as locked in the lockedResults array
	 *
	 * @param txf the text field to be locked
	 * @param index the index of the text field
	 */
	private void lockResult (TextField txf, int index) {
		// Set the text in the text field to bold, blue and static size
		txf.setStyle("-fx-font-weight: bold; -fx-text-fill: blue; -fx-font-size: 7pt;");
		this.lockedResults[index] = Integer.parseInt(txf.getText());
	}

	/**
	 * Register the new sums, bonus and total
	 */
	private void updateSums () {
		// Make an array that replaces the -1s with 0s of the lockedResults array
		// Otherwise there would be a bunch of -1s in the sums and that wouldn't work
		int[] onlyLockedResults = Arrays.stream(this.lockedResults).map(result -> (result != -1) ? result : 0).toArray();

		// Set the text of the singles-sum to the new sum
		// To do this we need to only grab the first 6 elements in the onlyLockedResults array
		this.txfSumBonusSumTotal[0].setText(Integer.toString(Arrays.stream(Arrays.copyOfRange(onlyLockedResults, 0, 6)).sum()));
		// Set the text of the bonus to 50 if the singles-sum is larger than or equal to 63
		this.txfSumBonusSumTotal[1].setText(Integer.toString((Integer.parseInt(this.txfSumBonusSumTotal[0].getText()) >= 63) ? 50 : 0));
		// Set the text of the others-sum to the new sum
		// To do this we need to only grab the 6th and onwards elements in the onlyLockedResults array
		this.txfSumBonusSumTotal[2].setText(Integer.toString(Arrays.stream(Arrays.copyOfRange(onlyLockedResults, 6, this.lockedResults.length)).sum()));
		// Set the text of the total to the sum of the locked results plus the bonus
		this.txfSumBonusSumTotal[3].setText(Integer.toString(Arrays.stream(onlyLockedResults).sum() + Integer.parseInt(this.txfSumBonusSumTotal[1].getText())));
	}

	/**
	 * Reset the dice face values, result text fields, hold checkboxes and Roll button
	 */
	private void resetRolls () {
		this.dice.setValues(new int[]{0, 0, 0, 0, 0});
		this.dice.resetThrowCount();
		this.updateValues();
		this.updateResults();
		this.rollAndHoldsDisabled(false);
		this.resetHolds();

		for (int i=0; i<this.lockedResults.length; i++)
			this.txfResults[i].setDisable(this.lockedResults[i] == -1);

		// If the Yatzy text field is not locked, then we need to set it to 0
		// Otherwise it will say 50, because the 5, 0s are technically a Yatzy
		if (this.lockedResults[14] == -1) {
			this.txfResults[14].setText(Integer.toString(0));
			this.txfResults[14].setDisable(true);
		}
	}

	/**
	 * Register all the hold checkboxes as not selected
	 */
	private void resetHolds () {
		for (CheckBox chbHold : this.chbHolds) chbHold.setSelected(false);
	}

	/**
	 * Handle the game over actions
	 */
	private void gameOver () {
		// Create a new Alert object to tell the player that they are done with the game,
		// and tell them, how many points that they made
		Alert popup = new Alert(Alert.AlertType.INFORMATION);
		popup.setTitle("Game Over");
		popup.setHeaderText("You have ended the game by locking in all your results!");
		popup.setContentText(String.format("You got %s points! Have a great day", this.txfSumBonusSumTotal[3].getText()));
		// Wait for the user to click the OK button
		popup.showAndWait();

		// Close the window and terminate the application
		Platform.exit();
		System.exit(0);
	}
}
