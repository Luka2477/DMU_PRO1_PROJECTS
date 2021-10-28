package gui;

import javafx.application.Application;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Yatzy;
import org.w3c.dom.Text;

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
	// Shows points in sums, bonus and total.
	private TextField txfSumSame, txfBonus, txfSumOther, txfTotal;
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

			this.txfValues[i] = txfValue;
			dicePane.add(this.txfValues[i], i, 0);

			this.chbHolds[i] = chbHold;
			dicePane.add(this.chbHolds[i], i, 1);
		}

		Button btnRoll = new Button("Roll");
		btnRoll.setFont(new Font(20));
		btnRoll.setOnAction(event -> this.handleBtnRoll());
		GridPane.setHalignment(btnRoll, HPos.CENTER);

		Label lblRolled = new Label("Rolled: 0");
		lblRolled.setFont(new Font(10));
		GridPane.setHalignment(lblRolled, HPos.CENTER);

		this.btnRoll = btnRoll;
		dicePane.add(this.btnRoll, 3, 2);

		this.lblRolled = lblRolled;
		dicePane.add(this.lblRolled, 4, 2);

		// ---------------------------------------------------------------------

		GridPane scorePane = new GridPane();
		pane.add(scorePane, 0, 1);
		scorePane.setGridLinesVisible(false);
		scorePane.setPadding(new Insets(10));
		scorePane.setVgap(5);
		scorePane.setHgap(10);
		scorePane.setStyle("-fx-border-color: black");
		int w = 50; // width of the text fields

		// Initialize labels for results, txfResults,
		// labels and text fields for sums, bonus and total.
		// TODO
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

			scorePane.add(lblResult, 0, i);
			this.txfResults[i] = txfResult;
			scorePane.add(this.txfResults[i], 1, i);
		}

		Label lblSumSame = new Label("Sum:");
		lblSumSame.setFont(new Font(10));

		TextField txfSumSame = new TextField("0");
		txfSumSame.setEditable(false);
		txfSumSame.setPrefWidth(w);
		txfSumSame.setFont(new Font(10));
		txfSumSame.setAlignment(Pos.CENTER_RIGHT);
		txfSumSame.setStyle("-fx-font-weight: bold; -fx-text-fill: blue;");

		Label lblBonus = new Label("Bonus:");
		lblBonus.setFont(new Font(10));

		TextField txfBonus = new TextField("0");
		txfBonus.setEditable(false);
		txfBonus.setPrefWidth(w);
		txfBonus.setFont(new Font(10));
		txfBonus.setAlignment(Pos.CENTER_RIGHT);
		txfBonus.setStyle("-fx-font-weight: bold; -fx-text-fill: blue;");

		scorePane.add(lblSumSame, 2, 5);
		this.txfSumSame = txfSumSame;
		scorePane.add(this.txfSumSame, 3, 5);

		scorePane.add(lblBonus, 4, 5);
		this.txfBonus = txfBonus;
		scorePane.add(this.txfBonus, 5, 5);
	}

	// -------------------------------------------------------------------------

	private Yatzy dice = new Yatzy();

	// Create a method for btnRoll's action.
	// Hint: Create small helper methods to be used in the action method.
	// TODO
	private void handleBtnRoll () {

	}

	// -------------------------------------------------------------------------

	// Create a method for mouse click on one of the text fields in txfResults.
	// Hint: Create small helper methods to be used in the mouse click method.
	// TODO

}
