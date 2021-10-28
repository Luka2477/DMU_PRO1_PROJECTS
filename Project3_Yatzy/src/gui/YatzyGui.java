package gui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Yatzy;

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

		this.txfValues = new TextField[5];
		this.chbHolds = new CheckBox[5];

		for (int i=0; i<this.txfValues.length; i++) {
			TextField txfValue = new TextField();
			txfValue.setEditable(false);
			txfValue.setPrefSize(75, 75);
			txfValue.setFont(new Font(35));
			txfValue.setAlignment(Pos.CENTER);

			CheckBox chbHold = new CheckBox("Hold");
			chbHold.setFont(new Font(10));
			GridPane.setHalignment(chbHold, HPos.CENTER);

			dicePane.add(txfValue, i, 0);
			this.txfValues[i] = txfValue;

			dicePane.add(chbHold, i, 1);
			this.chbHolds[i] = chbHold;
		}

		Button btnRoll = new Button("Roll");
		btnRoll.setFont(new Font(20));
		btnRoll.setOnAction(event -> this.handleBtnRoll());
		GridPane.setHalignment(btnRoll, HPos.CENTER);

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

		String[] lblResults = {"1-s", "2-s", "3-s", "4-s", "5-s", "6-s", "One pair", "Two pairs",
				"Three same", "Four same", "Full House", "Small Straight", "Large Straight", "Chance", "Yatzy"};
		this.txfResults = new TextField[15];

		for (int i=0; i<this.txfResults.length; i++) {
			Label lblResult = new Label(lblResults[i]);
			lblResult.setFont(new Font(10));

			TextField txfResult = new TextField();
			txfResult.setEditable(false);
			txfResult.setDisable(true);
			txfResult.setPrefWidth(w);
			txfResult.setFont(new Font(10));
			txfResult.setAlignment(Pos.CENTER_RIGHT);
			txfResult.setOnAction(this::handleLockResult);

			scorePane.add(lblResult, 0, i);
			scorePane.add(txfResult, 1, i);
			this.txfResults[i] = txfResult;
		}

		String[][] lbls = {{"Sum:", "Bonus:"}, {"Sum:", "Total:"}};
		int[] rows = {5, 14};
		this.txfSumBonusSumTotal = new TextField[4];

		for (int row=0; row<rows.length; row++) {
			for (int col=0; col<lbls.length; col++) {
				Label lbl = new Label(lbls[row][col]);
				lbl.setFont(new Font(10));

				TextField txf = new TextField("0");
				txf.setEditable(false);
				txf.setPrefWidth(w);
				txf.setFont(new Font(10));
				txf.setAlignment(Pos.CENTER_RIGHT);
				txf.setStyle("-fx-font-weight: bold; -fx-text-fill: blue;");

				scorePane.add(lbl, col * 2 + 2, rows[row]);
				scorePane.add(txf, col * 2 + 3, rows[row]);
				this.txfSumBonusSumTotal[row * 2 + col] = txf;
			}
		}
	}

	// -------------------------------------------------------------------------

	private Yatzy dice = new Yatzy();
	private final int[] lockedResults = new int[15];

	private void handleBtnRoll () {
		this.throwDice();

		this.updateValues();

		if (this.dice.getThrowCount() == 3) this.lastThrow();

		this.updateResults();
	}

	private void throwDice() {
		boolean[] holds = new boolean[5];
		for (int i=0; i<holds.length; i++) holds[i] = this.chbHolds[i].isSelected();

		this.dice.throwDice(holds);
	}

	private void updateValues () {
		int[] faceValues = this.dice.getValues();
		for (int i=0; i<faceValues.length; i++) this.txfValues[i].setText(Integer.toString(faceValues[i]));
		this.lblRolled.setText("Rolled: " + this.dice.getThrowCount());
	}

	private void lastThrow () {
		this.btnRoll.setDisable(true);
		for (CheckBox chbHold : this.chbHolds) chbHold.setDisable(true);
	}

	private void updateResults () {
		int[] results = this.dice.getResults();
		for (int i=0; i<results.length; i++) {
			if (this.lockedResults[i] == 0) {
				int result = results[i];
				this.txfResults[i].setText(Integer.toString(result));
				this.txfResults[i].setDisable(result == 0);
			}
		}
	}

	// -------------------------------------------------------------------------

	// Create a method for mouse click on one of the text fields in txfResults.
	// Hint: Create small helper methods to be used in the mouse click method.
	// TODO
	private void handleLockResult (Event event) {
		TextField txfResult = (TextField) event.getSource();
	}
}
