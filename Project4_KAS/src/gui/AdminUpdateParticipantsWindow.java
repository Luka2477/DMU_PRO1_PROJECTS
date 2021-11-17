package gui;

import application.model.Participant;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminUpdateParticipantsWindow extends Stage {

    private final Participant participant;

    private TextField txfName, txfAddress, txfCountry, txfCity, txfTelephone;

    AdminUpdateParticipantsWindow (Participant participant) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opdatere deltager - KAS");

        this.participant = participant;

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private void initContent (GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // -------------------------------------------------------------------------

        VBox vBox = new VBox();
        pane.add(vBox, 0, 0, 2, 1);

        Label lblInfo = new Label("BEMÆRK: vil du ændre på regstrationsinformationer-,");
        vBox.getChildren().add(lblInfo);

        Label lblInfo1 = new Label("kan det ikke gøres her.");
        vBox.getChildren().add(lblInfo1);

        Label lblName = new Label("Navn:");
        pane.add(lblName, 0, 1);

        Label lblAddress = new Label("Adresse:");
        pane.add(lblAddress, 0, 2);

        Label lblCountry = new Label("Land:");
        pane.add(lblCountry, 0, 3);

        Label lblCity = new Label("By:");
        pane.add(lblCity, 0, 4);

        Label lblTelephone = new Label("Tlf. nr:");
        pane.add(lblTelephone, 0, 5);

        this.txfName = new TextField();
        pane.add(this.txfName, 1, 1);

        this.txfAddress = new TextField();
        pane.add(this.txfAddress, 1, 2);

        this.txfCountry = new TextField();
        pane.add(this.txfCountry, 1, 3);

        this.txfCity = new TextField();
        pane.add(this.txfCity, 1, 4);

        this.txfTelephone = new TextField();
        pane.add(this.txfTelephone, 1, 5);

        // -------------------------------------------------------------------------

        Button btnCancel = new Button("Afslut");
        btnCancel.setOnAction(event -> this.cancelAction());
        pane.add(btnCancel, 0, 6);

        Button btnSave = new Button("Gem");
        btnSave.setOnAction(event -> this.saveAction());
        pane.add(btnSave, 1, 6);

        // -------------------------------------------------------------------------

        this.initControls();
    }

    // -------------------------------------------------------------------------

    private void initControls () {
        this.txfName.setText(this.participant.getName());
        this.txfAddress.setText(this.participant.getAddress());
        this.txfCountry.setText(this.participant.getCountry());
        this.txfCity.setText(this.participant.getCity());
        this.txfTelephone.setText(this.participant.getTelephone());
    }

    // -------------------------------------------------------------------------

    private void cancelAction () {
        this.hide();
    }

    private void saveAction () {
        String name = this.txfName.getText().trim();
        String address = this.txfAddress.getText().trim();
        String country = this.txfCountry.getText().trim();
        String city = this.txfCity.getText().trim();
        String telephone = this.txfTelephone.getText().trim();

        this.participant.setName(name);
        this.participant.setAddress(address);
        this.participant.setCountry(country);
        this.participant.setCity(city);
        this.participant.setTelephone(telephone);

        this.hide();
    }

}
