package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AdminPane extends GridPane {

    private final TextField txfUsername;
    private final PasswordField txfPassword;
    private final Label lblError;
    private final Button btnLogin;

    public AdminPane () {
        this.setPadding(new Insets(200, 400, 200, 400));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // --------------------------------------------------------------

        GridPane pane = new GridPane();
        pane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-radius: 10;");
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        this.add(pane, 0, 0);

        Label lblUsername = new Label("Brugernavn:");
        pane.add(lblUsername, 0, 0);

        this.txfUsername = new TextField();
        pane.add(this.txfUsername, 1, 0);

        Label lblPassword = new Label("Kode:");
        pane.add(lblPassword, 0, 1);

        this.txfPassword = new PasswordField();
        pane.add(this.txfPassword, 1, 1);

        this.lblError = new Label();
        this.lblError.setStyle("-fx-text-fill: red;");
        pane.add(this.lblError, 0, 2, 2, 1);

        this.btnLogin = new Button("Log in");
        this.btnLogin.setOnAction(event -> this.loginAction());
        this.btnLogin.setDefaultButton(true);
        GridPane.setHalignment(this.btnLogin, HPos.CENTER);
        pane.add(this.btnLogin, 0, 3, 2, 1);
    }

    // --------------------------------------------------------------

    private void loginAction () {
        if (this.txfUsername.getText().trim().equals("admin") && this.txfPassword.getText().trim().equals("admin")) {
            AdminWindow adminWindow = new AdminWindow();
            adminWindow.show();

            this.txfUsername.clear();
            this.txfPassword.clear();
            this.lblError.setText("");
        } else {
            this.lblError.setText("Brugernavn eller kode er forkert!");
        }
    }

}
